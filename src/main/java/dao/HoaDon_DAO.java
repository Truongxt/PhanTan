package dao;

import entity.*;
import interfaces.IHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDon_DAO extends UnicastRemoteObject implements IHoaDon {

    private final EntityManagerFactory emf;

    public HoaDon_DAO() throws RemoteException {
        emf = Persistence.createEntityManagerFactory("default"); //  "default" persistence unit
    }

    protected HoaDon_DAO(int port) throws RemoteException {
        super(port);
        emf = Persistence.createEntityManagerFactory("default");
    }

    protected HoaDon_DAO(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public Boolean create(HoaDon hoaDon)   {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hoaDon);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> getAllHoaDon() {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaQuery<HoaDon> query = em.getCriteriaBuilder().createQuery(HoaDon.class);
            query.select(query.from(HoaDon.class));
            List<HoaDon> resultList = em.createQuery(query).getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }

    @Override
    public HoaDon getHoaDon(String maHD) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(HoaDon.class, maHD);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean suaHoaDon(String maHD, HoaDon newHoaDon) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            HoaDon hoaDon = em.find(HoaDon.class, maHD);
            if (hoaDon != null) {
                hoaDon.setNgayLap(newHoaDon.getNgayLap());
                hoaDon.setTongTien(newHoaDon.getTongTien());
               // hoaDon.setVoucher(newHoaDon.getVoucher());
                hoaDon.setKhachHang(newHoaDon.getKhachHang());
                hoaDon.setNhanVien(newHoaDon.getNhanVien());
                hoaDon.setKetToan(newHoaDon.getKetToan());
                hoaDon.setAtm(newHoaDon.isAtm());
                hoaDon.setTienDaDua(newHoaDon.getTienDaDua());
                hoaDon.setTrangThai(newHoaDon.isTrangThai());
                em.merge(hoaDon);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteHoaDon(String maHD) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            HoaDon hoaDon = em.find(HoaDon.class, maHD);
            if (hoaDon != null) {
                em.remove(hoaDon);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSize() {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(cb.count(root));
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<ChiTietHoaDon> getChiTietHoaDon(String maHD) {
        EntityManager em = emf.createEntityManager();
        try {
            List<ChiTietHoaDon> resultList = em.createQuery(
                            "SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHD = :maHD", ChiTietHoaDon.class)
                    .setParameter("maHD", maHD)
                    .getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<ThangVaDoanhThu> getDoanhThuTheoThang(int nam) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Object[]> results = em.createQuery(
                            "SELECT MONTH(h.ngayLap), SUM(h.tongTien) FROM HoaDon h WHERE YEAR(h.ngayLap) = :nam GROUP BY MONTH(h.ngayLap) ORDER BY MONTH(h.ngayLap)")
                    .setParameter("nam", nam)
                    .getResultList();
            ArrayList<ThangVaDoanhThu> list = new ArrayList<>();
            for (Object[] result : results) {
                list.add(new ThangVaDoanhThu((Integer) result[0], (Double) result[1]));
            }
            return list;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<ThangVaDoanhThu> getDoanhThuTheoNgay(int thang, int nam) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Object[]> results = em.createQuery(
                            "SELECT DAY(h.ngayLap), SUM(h.tongTien) FROM HoaDon h WHERE MONTH(h.ngayLap) = :thang AND YEAR(h.ngayLap) = :nam GROUP BY DAY(h.ngayLap) ORDER BY DAY(h.ngayLap)")
                    .setParameter("thang", thang)
                    .setParameter("nam", nam)
                    .getResultList();
            ArrayList<ThangVaDoanhThu> list = new ArrayList<>();
            for (Object[] result : results) {
                list.add(new ThangVaDoanhThu((Integer) result[0], (Double) result[1]));
            }
            return list;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSizeOfMonth(int month, int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, root.get("ngayLap")), month);
            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year);
            query.select(cb.count(root)).where(cb.and(monthPredicate, yearPredicate));
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public int getSizeHoaDonTheoNgay(int day, int month, int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            Predicate dayPredicate = cb.equal(cb.function("DAY", Integer.class, root.get("ngayLap")), day);
            Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, root.get("ngayLap")), month);
            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year);
            query.select(cb.count(root)).where(cb.and(dayPredicate, monthPredicate, yearPredicate));
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> getTatCaHoaDonTrongKetToan(String maKetToan) {
        EntityManager em = emf.createEntityManager();
        try {
            List<HoaDon> resultList = em.createQuery(
                            "SELECT h FROM HoaDon h WHERE h.ketToan.maKetToan = :maKetToan", HoaDon.class)
                    .setParameter("maKetToan", maKetToan)
                    .getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> filter(String maHD, String sdt, double doanhThu, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HoaDon> query = cb.createQuery(HoaDon.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            List<Predicate> predicates = new ArrayList<>();

            if (maHD != null && !maHD.isEmpty()) {
                predicates.add(cb.equal(root.get("maHD"), maHD));
            }
            if (sdt != null && !sdt.isEmpty()) {
                predicates.add(cb.equal(root.get("khachHang").get("sdt"), sdt));
            }
            if (doanhThu > 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("tongTien"), doanhThu));
            }
            if (ngayBatDau != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("ngayLap"), ngayBatDau));
            }
            if (ngayKetThuc != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("ngayLap"), ngayKetThuc));
            }

            query.where(predicates.toArray(new Predicate[0]));
            List<HoaDon> resultList = em.createQuery(query).getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> getAllOrderInAcountingVoucher(String maKetToan) {
        EntityManager em = emf.createEntityManager();
        try {
            List<HoaDon> resultList = em.createQuery(
                            "SELECT h FROM HoaDon h WHERE h.ketToan.maKetToan = :maKetToan", HoaDon.class)
                    .setParameter("maKetToan", maKetToan)
                    .getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }

    @Override
    public List<HoaDon> getHoaDonSuggestions(String keyword) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HoaDon> query = cb.createQuery(HoaDon.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(root).where(cb.like(root.get("maHD"), "%" + keyword + "%"));
            return em.createQuery(query).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public String generateID(NhanVien nv) {
        EntityManager em = emf.createEntityManager();
        String result = "HD";
        LocalDate time = LocalDate.now();
        java.time.format.DateTimeFormatter dateFormater = java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy");
        result += dateFormater.format(time);
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HoaDon> query = cb.createQuery(HoaDon.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(root).where(cb.like(root.get("maHD"), result + "%")).orderBy(cb.desc(root.get("maHD")));
            List<HoaDon> lastOrders = em.createQuery(query).setMaxResults(1).getResultList();
            if (!lastOrders.isEmpty()) {
                String lastID = lastOrders.get(0).getMaHD();
                String sNumber = lastID.substring(lastID.length() - 5);
                int num = Integer.parseInt(sNumber) + 1;
                result += String.format("%05d", num);
            } else {
                result += String.format("%05d", 1);
            }
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public HoaDon createNewOrder(NhanVien nv) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            HoaDon order = new HoaDon(generateID(nv));
            order.setTrangThai(false);
            LocalDate now = LocalDate.now();
            order.setNgayLap(now);
            em.persist(order);
            em.getTransaction().commit();
            return order;
        }
        catch(Exception e){
            e.printStackTrace();
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return null;
        }
        finally{
            em.close();
        }

    }

    @Override
    public ArrayList<HoaDon> getAllHoaDonTam() {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HoaDon> query = cb.createQuery(HoaDon.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(root).where(cb.equal(root.get("trangThai"), false));
            List<HoaDon> resultList = em.createQuery(query).getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateOrderAcountingVoucher(String orderID, String acountingVoucherID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            HoaDon hoaDon = em.find(HoaDon.class, orderID);
            KetToan ketToan = em.find(KetToan.class, acountingVoucherID);
            if (hoaDon != null && ketToan != null) {
                hoaDon.setKetToan(ketToan);
                em.merge(hoaDon);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSoLuongKhachHangThang(int month, int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, root.get("ngayLap")), month);
            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year);
            query.select(cb.countDistinct(root.get("khachHang"))).where(cb.and(monthPredicate, yearPredicate));
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public double getDoanhThuCuaNam(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Double> query = cb.createQuery(Double.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(cb.sum(root.get("tongTien"))).where(cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year));
            Double result = em.createQuery(query).getSingleResult();
            return (result != null) ? result : 0.0;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSoLuongKhachHangNgay(int day, int month, int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            Predicate dayPredicate = cb.equal(cb.function("DAY", Integer.class, root.get("ngayLap")), day);
            Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, root.get("ngayLap")), month);
            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year);

            query.select(cb.countDistinct(root.get("khachHang"))).where(cb.and(dayPredicate, monthPredicate, yearPredicate));
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public int getSizeOfYear(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year);
            query.select(cb.count(root)).where(yearPredicate);
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public int getSoLuongKhachHangNam(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, root.get("ngayLap")), year);
            query.select(cb.countDistinct(root.get("khachHang"))).where(yearPredicate);
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public int getSoHoaDonTheoKhachHang(String maKH) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(cb.count(root)).where(cb.equal(root.get("khachHang").get("maKH"), maKH));
            return em.createQuery(query).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public double getDoanhThuTheoKhachHang(String maKH) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Double> query = cb.createQuery(Double.class);
            Root<HoaDon> root = query.from(HoaDon.class);
            query.select(cb.sum(root.get("tongTien"))).where(cb.equal(root.get("khachHang").get("maKH"), maKH));
            Double result = em.createQuery(query).getSingleResult();
            return (result != null) ? result : 0.0;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> getAllHoaDonTrongKetToan(Date ngayBatDau, Date ngayKetThuc) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HoaDon> query = cb.createQuery(HoaDon.class);
            Root<HoaDon> root = query.from(HoaDon.class);

            // Convert java.util.Date to java.sql.Date for the query
            java.sql.Date sqlNgayBatDau = new java.sql.Date(ngayBatDau.getTime());
            java.sql.Date sqlNgayKetThuc = new java.sql.Date(ngayKetThuc.getTime());

            Predicate ngayBatDauPredicate = cb.greaterThanOrEqualTo(root.get("ngayLap"), sqlNgayBatDau.toLocalDate());
            Predicate ngayKetThucPredicate = cb.lessThanOrEqualTo(root.get("ngayLap"), sqlNgayKetThuc.toLocalDate());

            query.where(cb.and(ngayBatDauPredicate, ngayKetThucPredicate));
            List<HoaDon> resultList = em.createQuery(query).getResultList();
            return new ArrayList<>(resultList);
        } finally {
            em.close();
        }
    }
}
