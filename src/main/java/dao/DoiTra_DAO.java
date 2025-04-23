package dao;

import entity.ChiTietDoiTra;
import entity.DoiTra;
import interfaces.IDoiTra;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DoiTra_DAO extends UnicastRemoteObject implements IDoiTra {

    private final EntityManagerFactory emf;

    public DoiTra_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public DoiTra getOne(String id) throws Exception {
        var em = emf.createEntityManager();
        try {
            return em.find(DoiTra.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DoiTra> getAll() throws Exception {
        var em = emf.createEntityManager();
        try {
            List<DoiTra> result = em.createQuery("SELECT d FROM DoiTra d", DoiTra.class).getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(DoiTra doiTra) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("== Kiểm tra DoiTra trước persist ==");
            if (doiTra == null) throw new RuntimeException("DoiTra bị null!");

            System.out.println("Mã HDDT: " + doiTra.getMaHDDT());
            System.out.println("Nhân viên: " + (doiTra.getNhanvien() != null ? doiTra.getNhanvien().getMaNhanVien() : "null"));
            System.out.println("Hóa đơn: " + (doiTra.getHoaDon() != null ? doiTra.getHoaDon().getMaHD() : "null"));
            System.out.println("Lý do: " + doiTra.getLiDO());
            System.out.println("Danh sách ChiTietDoiTra:");

            if (doiTra.getListDetail() == null) {
                throw new RuntimeException("ListDetail bị null");
            }

            for (ChiTietDoiTra ctdt : doiTra.getListDetail()) {
                if (ctdt == null) {
                    throw new RuntimeException("Một phần tử trong listDetail bị null");
                }
                if (ctdt.getThuoc() == null) {
                    throw new RuntimeException("Thuốc trong ChiTietDoiTra bị null");
                }
                if (ctdt.getDoiTra() == null) {
                    throw new RuntimeException("ChiTietDoiTra chưa được gán doiTra");
                }
                System.out.println(" - Thuốc: " + ctdt.getThuoc().getMaThuoc() + ", SL: " + ctdt.getSoLuong() + ", Giá: " + ctdt.thanhTien());
            }

            em.persist(doiTra);
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
    public boolean update(String id, DoiTra doiTra) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            DoiTra existingEntity = em.find(DoiTra.class, id);
            if (existingEntity != null) {
                existingEntity.setNgayDoiTra(doiTra.getNgayDoiTra());
                existingEntity.setLoai(doiTra.isLoai());
                existingEntity.setTienTra(doiTra.getTienTra());
                existingEntity.setLiDO(doiTra.getLiDO());
                em.merge(existingEntity);
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

    @Override
    public boolean isReturnOrderExist(String maHoaDon) throws Exception {
        var em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(d) FROM DoiTra d WHERE d.hoaDon.maHD = :maHoaDon", Long.class)
                    .setParameter("maHoaDon", maHoaDon)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DoiTra> findById(String returnOrderID) throws Exception {
        var em = emf.createEntityManager();
        try {
            List<DoiTra> result = em.createQuery(
                            "SELECT d FROM DoiTra d WHERE d.maHDDT LIKE :returnOrderID", DoiTra.class)
                    .setParameter("returnOrderID", returnOrderID + "%")
                    .getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DoiTra> filter(int type) throws Exception {
        var em = emf.createEntityManager();
        try {
            String query = "SELECT d FROM DoiTra d WHERE d.loai = :type";
            List<DoiTra> result = em.createQuery(query, DoiTra.class)
                    .setParameter("type", type == 1)
                    .getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public int getNumberOfReturnOrderInMonth(int month, int year) throws Exception {
        var em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(d) FROM DoiTra d WHERE MONTH(d.ngayDoiTra) = :month AND YEAR(d.ngayDoiTra) = :year", Long.class)
                    .setParameter("month", month)
                    .setParameter("year", year)
                    .getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public double getTotalReturnOrderInMonth(int month, int year) throws Exception {
        var em = emf.createEntityManager();
        try {
            Double total = em.createQuery(
                            "SELECT SUM(d.tienTra) FROM DoiTra d WHERE MONTH(d.ngayDoiTra) = :month AND YEAR(d.ngayDoiTra) = :year", Double.class)
                    .setParameter("month", month)
                    .setParameter("year", year)
                    .getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }

    @Override
    public DoiTra calculateTotals() throws Exception {
        var em = emf.createEntityManager();
        try {
            Object[] result = em.createQuery(
                            "SELECT SUM(CASE WHEN d.loai = true THEN 1 ELSE 0 END), " +
                                    "SUM(CASE WHEN d.loai = false THEN 1 ELSE 0 END), " +
                                    "SUM(CASE WHEN d.loai = true THEN d.tienTra ELSE 0 END) " +
                                    "FROM DoiTra d", Object[].class)
                    .getSingleResult();

            int totalReturns = 0;
            int totalExchanges = 0;
            double totalAmount = 0.0;

            if (result != null) {
                if (result[0] != null) totalReturns = ((Number) result[0]).intValue();
                if (result[1] != null) totalExchanges = ((Number) result[1]).intValue();
                if (result[2] != null) totalAmount = ((Number) result[2]).doubleValue();
            }

            return new DoiTra(totalExchanges, totalReturns, totalAmount);
        } finally {
            em.close();
        }
    }

}