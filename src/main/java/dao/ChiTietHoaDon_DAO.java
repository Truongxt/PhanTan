
        package dao;

import entity.ChiTietHoaDon;
import entity.Thuoc;
import entity.HoaDon;
import entity.ThuocVaLuotBan;
import entity.ThuocvaDoanhThu;
import interfaces.IChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietHoaDon_DAO extends UnicastRemoteObject implements IChiTietHoaDon {
    private static final Logger LOGGER = Logger.getLogger(ChiTietHoaDon_DAO.class.getName());
    private final EntityManagerFactory emf;

    public ChiTietHoaDon_DAO() throws RemoteException {
        super();
        try {
            emf = Persistence.createEntityManagerFactory("default");
            if (emf == null) {
                throw new IllegalStateException("Không thể khởi tạo EntityManagerFactory");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi khởi tạo EntityManagerFactory: " + e.getMessage(), e);
            throw new RemoteException("Không thể khởi tạo ChiTietHoaDon_DAO", e);
        }
    }

    @Override
    public boolean create(ChiTietHoaDon chiTiet) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(chiTiet);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tạo ChiTietHoaDon: " + e.getMessage(), e);
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery("SELECT c FROM ChiTietHoaDon c", ChiTietHoaDon.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy tất cả ChiTietHoaDon: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public ChiTietHoaDon getChiTietHoaDon(String maThuoc, String maHoaDon) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT c FROM ChiTietHoaDon c WHERE c.thuoc.maThuoc = :maThuoc AND c.hoaDon.maHD = :maHD",
                            ChiTietHoaDon.class)
                    .setParameter("maThuoc", maThuoc)
                    .setParameter("maHD", maHoaDon)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy ChiTietHoaDon: " + e.getMessage(), e);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean suaChiTietHoaDon(String maThuoc, String maHoaDon, ChiTietHoaDon newChiTiet) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            ChiTietHoaDon existing = getChiTietHoaDon(maThuoc, maHoaDon);
            if (existing != null) {
                existing.setSoLuong(newChiTiet.getSoLuong());
                existing.setDonGia(newChiTiet.getDonGia());
                em.merge(existing);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi sửa ChiTietHoaDon: " + e.getMessage(), e);
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean deleteChiTietHoaDon(String maThuoc, String maHoaDon) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            ChiTietHoaDon chiTiet = getChiTietHoaDon(maThuoc, maHoaDon);
            if (chiTiet != null) {
                em.remove(em.merge(chiTiet));
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa ChiTietHoaDon: " + e.getMessage(), e);
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public int getSize() throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Long count = em.createQuery("SELECT COUNT(c) FROM ChiTietHoaDon c", Long.class).getSingleResult();
            return count.intValue();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy số lượng ChiTietHoaDon: " + e.getMessage(), e);
            return 0;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ThuocvaDoanhThu> getTop10ThuocCoDoanhThuCaoNhat() throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            List<Object[]> results = em.createQuery(
                            "SELECT c.thuoc, SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c " +
                                    "GROUP BY c.thuoc ORDER BY SUM(c.soLuong * c.donGia) DESC",
                            Object[].class)
                    .setMaxResults(10)
                    .getResultList();

            List<ThuocvaDoanhThu> list = new ArrayList<>();
            for (Object[] obj : results) {
                list.add(new ThuocvaDoanhThu((Thuoc) obj[0], (Double) obj[1]));
            }
            return list;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy top 10 thuốc có doanh thu cao nhất: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public double getDoanhThu(String maThuoc) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Double doanhThu = em.createQuery(
                            "SELECT SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c WHERE c.thuoc.maThuoc = :maThuoc",
                            Double.class)
                    .setParameter("maThuoc", maThuoc)
                    .getSingleResult();
            return doanhThu != null ? doanhThu : 0.0;
        } catch (NoResultException e) {
            return 0.0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy doanh thu cho thuốc " + maThuoc + ": " + e.getMessage(), e);
            throw new RemoteException("Lỗi khi lấy doanh thu", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public int getsoLuongBan(String maThuoc) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Long soLuong = em.createQuery(
                            "SELECT SUM(c.soLuong) FROM ChiTietHoaDon c WHERE c.thuoc.maThuoc = :maThuoc",
                            Long.class)
                    .setParameter("maThuoc", maThuoc)
                    .getSingleResult();
            return soLuong != null ? soLuong.intValue() : 0;
        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy số lượng bán cho thuốc " + maThuoc + ": " + e.getMessage(), e);
            throw new RemoteException("Lỗi khi lấy số lượng bán", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ThuocVaLuotBan> getThuocCoLuotBanCaoNhatTrongThang(int thang, int nam) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            LocalDate start = YearMonth.of(nam, thang).atDay(1);
            LocalDate end = YearMonth.of(nam, thang).atEndOfMonth();

            List<Object[]> results = em.createQuery(
                            "SELECT c.thuoc, SUM(c.soLuong), SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c " +
                                    "WHERE c.hoaDon.ngayLap BETWEEN :start AND :end " +
                                    "GROUP BY c.thuoc ORDER BY SUM(c.soLuong) DESC",
                            Object[].class)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getResultList();

            List<ThuocVaLuotBan> list = new ArrayList<>();
            for (Object[] obj : results) {
                list.add(new ThuocVaLuotBan((Thuoc) obj[0], (Long) obj[1], (Double) obj[2]));
            }
            return list;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy thuốc có lượt bán cao nhất trong tháng: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public ThuocVaLuotBan getTop1ThuocCoLuotBanCaoNhatTrongThang(int thang, int nam) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            LocalDate start = YearMonth.of(nam, thang).atDay(1);
            LocalDate end = YearMonth.of(nam, thang).atEndOfMonth();

            Object[] result = (Object[]) em.createQuery(
                            "SELECT c.thuoc, SUM(c.soLuong), SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c " +
                                    "WHERE c.hoaDon.ngayLap BETWEEN :start AND :end " +
                                    "GROUP BY c.thuoc ORDER BY SUM(c.soLuong) DESC",
                            Object[].class)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .setMaxResults(1)
                    .getSingleResult();

            return new ThuocVaLuotBan((Thuoc) result[0], (Long) result[1], (Double) result[2]);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy top 1 thuốc có lượt bán cao nhất trong tháng: " + e.getMessage(), e);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public ThuocvaDoanhThu getTop1ThuocCoDoanhThuCaoNhatTrongThang(int thang, int nam) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            LocalDate start = YearMonth.of(nam, thang).atDay(1);
            LocalDate end = YearMonth.of(nam, thang).atEndOfMonth();

            Object[] result = (Object[]) em.createQuery(
                            "SELECT c.thuoc, SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c " +
                                    "WHERE c.hoaDon.ngayLap BETWEEN :start AND :end " +
                                    "GROUP BY c.thuoc ORDER BY SUM(c.soLuong * c.donGia) DESC",
                            Object[].class)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .setMaxResults(1)
                    .getSingleResult();

            return new ThuocvaDoanhThu((Thuoc) result[0], (Double) result[1]);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy top 1 thuốc có doanh thu cao nhất trong tháng: " + e.getMessage(), e);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public ArrayList<ThuocVaLuotBan> getThuocTheoThang(int month, int year) throws RemoteException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String jpql = "SELECT NEW entity.ThuocVaLuotBan(t, SUM(ct.soLuong), SUM(ct.soLuong * ct.donGia)) " +
                    "FROM ChiTietHoaDon ct JOIN ct.thuoc t JOIN ct.hoaDon hd " +
                    "WHERE MONTH(hd.ngayLap) = :month AND YEAR(hd.ngayLap) = :year " +
                    "GROUP BY t";
            List<ThuocVaLuotBan> result = em.createQuery(jpql, ThuocVaLuotBan.class)
                    .setParameter("month", month)
                    .setParameter("year", year)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy thuốc theo tháng: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
