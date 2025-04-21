package dao;

import entity.BangKiemTien;
import entity.HoaDon;
import entity.KetToan;
import entity.KiemTien;
import gui.KetToan_GUI;
import interfaces.IKetToan;
import jakarta.persistence.*;
import main.Main;
import raven.toast.Notifications;
import utilities.AcountingVoucherPrinter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KetToan_DAO extends UnicastRemoteObject implements IKetToan {

    private final EntityManagerFactory emf;

    public KetToan_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public KetToan findById(String maKetToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            KetToan ketToan = em.find(KetToan.class, maKetToan);
            return ketToan;
        } finally {
            em.close();
        }
    }

    @Override
    public List<KetToan> findAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT k FROM KetToan k", KetToan.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(KetToan ketToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ketToan);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(KetToan ketToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ketToan);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(String maKetToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            KetToan ketToan = em.find(KetToan.class, maKetToan);
            if (ketToan != null) {
                em.remove(ketToan);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public List<KetToan> filterByDateRange(Date startDate, Date endDate) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT k FROM KetToan k WHERE k.ngayBatDau >= :startDate AND k.ngayKetThuc <= :endDate", KetToan.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    @Override
    public ArrayList<HoaDon> getAllHoaDon(Date start, Date end) throws RemoteException {
        ArrayList<HoaDon> allHoaDon = new HoaDon_DAO().getAllHoaDonTrongKetToan( new java.sql.Date(start.getTime()) , new java.sql.Date(end.getTime()));
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();

        return allHoaDon;
    }

    public String getMaMoiNhat(String maPrefix) {
        EntityManager em = emf.createEntityManager();
        try {
            List<String> result = em.createQuery(
                            "SELECT k.maKetToan FROM KetToan k WHERE k.maKetToan LIKE :prefix ORDER BY k.maKetToan DESC",
                            String.class)
                    .setParameter("prefix", maPrefix + "%")
                    .setMaxResults(1)
                    .getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public String taoMa(Date date) {
        String prefix = "KTOAN" + new SimpleDateFormat("ddMMyyyy").format(date);
        String maxId = getMaMoiNhat(prefix);
        if (maxId == null) {
            return prefix + "0000";
        }
        int number = Integer.parseInt(maxId.substring(maxId.length() - 4)) + 1;
        return prefix + String.format("%04d", number);
    }

    public double calculateTotalRevenue(String maKetToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Double total = em.createQuery("SELECT SUM(h.tongTien) FROM HoaDon h WHERE h.ketToan.maKetToan = :maKetToan", Double.class)
                    .setParameter("maKetToan", maKetToan)
                    .getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }

    public double getDoanhThu(List<HoaDon> list) {
        return list.stream().mapToDouble(HoaDon::getTongTien).sum();
    }

    public double getATM(List<HoaDon> list) {
        return list.stream().filter(HoaDon::isAtm).mapToDouble(HoaDon::getTongTien).sum();
    }

    public double getTong(List<KiemTien> list) {
        return list.stream().mapToDouble(KiemTien::getTong).sum();
    }
    public String TaoMa(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String format = dateFormat.format(date);
        String prefix = "KTOAN" + format;
        String maxID = getMaMoiNhat(prefix);
        if (maxID == null) {
            return prefix + "0000";
        } else {
            String bonKiTuCuoi = maxID.substring(maxID.length() - 4);
            int num = Integer.parseInt(bonKiTuCuoi);
            num++;
            return prefix + String.format("%04d", num);
        }
    }

    @Override
    public KetToan getKetToanCuoi() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT k FROM KetToan k ORDER BY k.ngayBatDau DESC";
            TypedQuery<KetToan> query = em.createQuery(jpql, KetToan.class);
            query.setMaxResults(1);
            List<KetToan> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public void taoPhieuKetToan(BangKiemTien bangKiemTien, Date ngayKetThuc) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Lấy ngày kết thúc của lần kết toán gần nhất
            Date ngayBatDau = getKetToanCuoi().getNgayKetThuc();

            // Lấy danh sách hóa đơn nằm trong khoảng kết toán
            List<HoaDon> listHoaDon = new HoaDon_DAO().getAllHoaDonTrongKetToan(ngayBatDau, ngayKetThuc);

            // Tạo mã kết toán
            String maKetToan = TaoMa(ngayKetThuc);

            // Tạo đối tượng kết toán
            KetToan ketToan = new KetToan(maKetToan, ngayBatDau, ngayKetThuc, bangKiemTien, listHoaDon);

            // Lưu bảng kiểm tiền và phiếu kết toán
            em.persist(bangKiemTien);
            em.persist(ketToan);

            // Cập nhật các hóa đơn để liên kết với phiếu kết toán mới tạo
            for (HoaDon hoaDon : listHoaDon) {
                hoaDon.setKetToan(ketToan); // Cập nhật quan hệ ManyToOne
                em.merge(hoaDon);
            }

            tx.commit();

            // Hiển thị thông báo và mở giao diện kết toán
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Tạo phiếu kết toán thành công");

            new Main().getMain().showForm(new KetToan_GUI(new Main().getTk()));

            // Tạo file PDF
            generatePDF(this.findById(maKetToan));

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            Logger.getLogger(KetToan_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<KetToan> getDSKetToanTheoNgay(LocalDate ngay) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT k FROM KetToan k WHERE k.ngayBatDau = :ngay";
            return em.createQuery(jpql, KetToan.class)
                    .setParameter("ngay", ngay)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void generatePDF(KetToan KetToan) {
//        tạo file pdf và hiển thị + in file pdf đó
        AcountingVoucherPrinter printer = new AcountingVoucherPrinter(KetToan);
        printer.generatePDF();
        AcountingVoucherPrinter.PrintStatus status = printer.printFile();
        if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_FILE) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
        } else if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_PRINTER) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
        }
    }


}
