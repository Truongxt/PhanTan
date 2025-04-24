package dao;

import entity.KhachHang;
import interfaces.IKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO extends UnicastRemoteObject implements IKhachHang {

    private final EntityManagerFactory emf;

    public KhachHang_DAO() throws RemoteException {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public boolean create(KhachHang kh) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(kh);
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
    public ArrayList<KhachHang> getAllKhachHang() {
        EntityManager em = emf.createEntityManager();
        try {
            List<KhachHang> result = em.createQuery("SELECT k FROM KhachHang k", KhachHang.class).getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<KhachHang> timKiemTheoMa(String maKhachHang) {
        EntityManager em = emf.createEntityManager();
        try {
            List<KhachHang> result = em.createQuery(
                            "SELECT k FROM KhachHang k WHERE k.maKhachHang = :ma", KhachHang.class)
                    .setParameter("ma", maKhachHang)
                    .getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public KhachHang getKhachHangSDT(String soDienThoai) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT k FROM KhachHang k WHERE k.sdt = :sdt", KhachHang.class)
                    .setParameter("sdt", soDienThoai)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public KhachHang getKhachHang(String maKH) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(KhachHang.class, maKH);
        } finally {
            em.close();
        }
    }

    @Override
    public String generateID() {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(k) FROM KhachHang k", Long.class).getSingleResult();
            return "KH" + (count + 1);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean taoMoi(KhachHang kh) {
        return create(kh);
    }

    @Override
    public boolean capNhat(String ma, KhachHang newKh) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            KhachHang existingKh = em.find(KhachHang.class, ma);
            if (existingKh != null) {
                existingKh.setTenKhachHang(newKh.getTenKhachHang());
                existingKh.setSdt(newKh.getSdt());
                existingKh.setDiaChi(newKh.getDiaChi());
                em.merge(existingKh);
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
    public ArrayList<KhachHang> getAllTKKhachHang() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<KhachHang> query = em.createQuery("SELECT k FROM KhachHang k", KhachHang.class);
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
    @Override
    public ArrayList<KhachHang> getAllTKKhachHangMonth(int month, int year) {
        var em = emf.createEntityManager();
        try {
            String jpql = "SELECT k FROM KhachHang k WHERE MONTH(k.ngayLapTaiKhoan) = :month AND YEAR(k.ngayLapTaiKhoan) = :year";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("month", month);
            query.setParameter("year", year);
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<KhachHang> getAllTKKhachHangYear(int year) {
        var em = emf.createEntityManager();
        try {
            String jpql = "SELECT k FROM KhachHang k WHERE YEAR(k.ngayLapTaiKhoan) = :year";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("year", year);
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<KhachHang> getTKKhachHangDoanhThu(String dau) {
        var em = emf.createEntityManager();
        try {
            String jpql = "SELECT k FROM KhachHang k WHERE k.diaChi LIKE :dau";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("dau", dau + "%");
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}