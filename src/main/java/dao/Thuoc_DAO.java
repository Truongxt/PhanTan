package dao;

import entity.Thuoc;
import interfaces.IThuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Thuoc_DAO extends UnicastRemoteObject implements IThuoc {

    private static final Logger LOGGER = Logger.getLogger(Thuoc_DAO.class.getName());
    private final EntityManagerFactory emf;

    public Thuoc_DAO(EntityManager em) throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public boolean create(Thuoc thuoc) throws RemoteException {
        if (thuoc == null || thuoc.getMaThuoc() == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(thuoc);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi tạo thuốc: " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Thuoc getThuocTheoMa(String maThuoc) throws RemoteException {
        if (maThuoc == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Thuoc.class, maThuoc);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy thuốc theo mã = " + maThuoc + ": " + e.getMessage(), e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Thuoc getThuocTheoTen(String tenThuoc) throws RemoteException {
        if (tenThuoc == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Thuoc t WHERE t.tenThuoc = :tenThuoc", Thuoc.class)
                    .setParameter("tenThuoc", tenThuoc)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy thuốc theo tên = " + tenThuoc + ": " + e.getMessage(), e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> getAllThuoc() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t", Thuoc.class)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách thuốc: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean suaThuoc(String maThuoc, Thuoc newThuoc) throws RemoteException {
        if (maThuoc == null || newThuoc == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Thuoc existingThuoc = em.find(Thuoc.class, maThuoc);
            if (existingThuoc != null) {
                existingThuoc.setTenThuoc(newThuoc.getTenThuoc());
                existingThuoc.setGia(newThuoc.getGia());
                existingThuoc.setHsd(newThuoc.getHsd());
                existingThuoc.setNsx(newThuoc.getNsx());
                existingThuoc.setThue(newThuoc.getThue());
                existingThuoc.setSoLuongTon(newThuoc.getSoLuongTon());
                existingThuoc.setMota(newThuoc.getMota());
                existingThuoc.setMaLoai(newThuoc.getMaLoai());
                existingThuoc.setMaXuatXu(newThuoc.getMaXuatXu());
                existingThuoc.setMaDonViTinh(newThuoc.getMaDonViTinh());
                existingThuoc.setMaNCC(newThuoc.getMaNCC());
                em.merge(existingThuoc);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi sửa thuốc với mã = " + maThuoc + ": " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSize() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(t) FROM Thuoc t", Long.class)
                    .getSingleResult();
            return count.intValue();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy số lượng thuốc: " + e.getMessage(), e);
            return 0;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> timKiemTheoMa(String ma) throws RemoteException {
        if (ma == null) {
            return new ArrayList<>();
        }
        EntityManager em = emf.createEntityManager();
        try {
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t WHERE t.maThuoc LIKE :ma", Thuoc.class)
                    .setParameter("ma", "%" + ma + "%")
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm thuốc theo mã = " + ma + ": " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean Xoa(String maThuoc) throws RemoteException {
        if (maThuoc == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Thuoc thuoc = em.find(Thuoc.class, maThuoc);
            if (thuoc != null) {
                em.remove(thuoc);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa thuốc với mã = " + maThuoc + ": " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> timKiemTheoTen(String ten) throws RemoteException {
        if (ten == null) {
            return new ArrayList<>();
        }
        EntityManager em = emf.createEntityManager();
        try {
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t WHERE t.tenThuoc LIKE :ten", Thuoc.class)
                    .setParameter("ten", "%" + ten + "%")
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm thuốc theo tên = " + ten + ": " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> filter(String loaiThuoc, String xuatXu, String ten, String ma) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder query = new StringBuilder("SELECT t FROM Thuoc t WHERE 1=1");
            if (ten != null && !ten.isEmpty()) {
                query.append(" AND t.tenThuoc LIKE :ten");
            }
            if (ma != null && !ma.isEmpty()) {
                query.append(" AND t.maThuoc LIKE :ma");
            }
            if (loaiThuoc != null && !loaiThuoc.isEmpty() && !loaiThuoc.equalsIgnoreCase("Tất cả")) {
                query.append(" AND t.maLoai.tenLoai = :loaiThuoc");
            }
            if (xuatXu != null && !xuatXu.isEmpty() && !xuatXu.equalsIgnoreCase("Tất cả")) {
                query.append(" AND t.maXuatXu.tenXuatXu = :xuatXu");
            }

            var typedQuery = em.createQuery(query.toString(), Thuoc.class);
            if (ten != null && !ten.isEmpty()) {
                typedQuery.setParameter("ten", "%" + ten + "%");
            }
            if (ma != null && !ma.isEmpty()) {
                typedQuery.setParameter("ma", "%" + ma + "%");
            }
            if (loaiThuoc != null && !loaiThuoc.isEmpty() && !loaiThuoc.equalsIgnoreCase("Tất cả")) {
                typedQuery.setParameter("loaiThuoc", loaiThuoc);
            }
            if (xuatXu != null && !xuatXu.isEmpty() && !xuatXu.equalsIgnoreCase("Tất cả")) {
                typedQuery.setParameter("xuatXu", xuatXu);
            }

            List<Thuoc> result = typedQuery.getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lọc thuốc: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> getThuocHetHan() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t WHERE t.hsd <= :currentDate", Thuoc.class)
                    .setParameter("currentDate", LocalDate.now())
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách thuốc hết hạn: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean capNhatSoLuong(Thuoc thuoc, int soLuong) throws RemoteException {
        if (thuoc == null || thuoc.getMaThuoc() == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Thuoc existingThuoc = em.find(Thuoc.class, thuoc.getMaThuoc());
            if (existingThuoc != null) {
                int newSoLuong = existingThuoc.getSoLuongTon() - soLuong;
                if (newSoLuong >= 0) {
                    existingThuoc.setSoLuongTon(newSoLuong);
                    em.merge(existingThuoc);
                    em.getTransaction().commit();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật số lượng thuốc: " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> getThuocTonKhoThap() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t WHERE t.soLuongTon < 100", Thuoc.class)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách thuốc tồn kho thấp: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> getThuocHetHan1Thang() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            LocalDate thresholdDate = LocalDate.now().plusMonths(1);
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t WHERE t.hsd <= :thresholdDate", Thuoc.class)
                    .setParameter("thresholdDate", thresholdDate)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách thuốc hết hạn trong 1 tháng: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Thuoc> getThuocTheoLoai(String tenLoai) throws RemoteException {
        if (tenLoai == null) {
            return new ArrayList<>();
        }
        EntityManager em = emf.createEntityManager();
        try {
            List<Thuoc> result = em.createQuery("SELECT t FROM Thuoc t WHERE t.maLoai.tenLoai = :tenLoai", Thuoc.class)
                    .setParameter("tenLoai", tenLoai)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách thuốc theo loại = " + tenLoai + ": " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public String generateID() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            String prefix = "SP";
            LocalDate time = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
            String datePart = dateFormatter.format(time);
            String pattern = prefix + datePart + "%";

            String lastID = em.createQuery("SELECT t.maThuoc FROM Thuoc t WHERE t.maThuoc LIKE :pattern ORDER BY t.maThuoc DESC", String.class)
                    .setParameter("pattern", pattern)
                    .setMaxResults(1)
                    .getSingleResult();

            String sNumber = lastID.substring(lastID.length() - 3); // Lấy 3 chữ số cuối
            int num = Integer.parseInt(sNumber) + 1;
            return prefix + datePart + String.format("%03d", num);
        } catch (NoResultException e) {
            // Nếu không có mã nào, bắt đầu từ 000
            String prefix = "SP";
            LocalDate time = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
            String datePart = dateFormatter.format(time);
            return prefix + datePart + String.format("%03d", 0);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tạo mã thuốc: " + e.getMessage(), e);
            return null;
        } finally {
            em.close();
        }
    }
}