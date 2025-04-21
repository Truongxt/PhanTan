package dao;

import entity.KhachHang;
import interfaces.IKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KhachHang_DAO extends UnicastRemoteObject implements IKhachHang {

    private final EntityManagerFactory emf;

    public KhachHang_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public Optional<KhachHang> findById(String maKhachHang) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            KhachHang khachHang = em.find(KhachHang.class, maKhachHang);
            return Optional.ofNullable(khachHang);
        } finally {
            em.close();
        }
    }

    @Override
    public List<KhachHang> findByTen(String tenKhachHang) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT k FROM KhachHang k WHERE k.tenKhachHang LIKE :ten", KhachHang.class)
                    .setParameter("ten", "%" + tenKhachHang + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public KhachHang findBySdt(String sdt) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT k FROM KhachHang k WHERE k.sdt = :sdt", KhachHang.class)
                    .setParameter("sdt", sdt).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(KhachHang khachHang) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(khachHang);
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
    public boolean update(KhachHang khachHang) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            KhachHang existingKhachHang = em.find(KhachHang.class, khachHang.getMaKhachHang());
            if (existingKhachHang != null) {
                existingKhachHang.setTenKhachHang(khachHang.getTenKhachHang());
                existingKhachHang.setSdt(khachHang.getSdt());
                existingKhachHang.setDiaChi(khachHang.getDiaChi());
                existingKhachHang.setNgayLapTaiKhoan(khachHang.getNgayLapTaiKhoan());
                em.merge(existingKhachHang);
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
    public boolean delete(String maKhachHang) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            KhachHang khachHang = em.find(KhachHang.class, maKhachHang);
            if (khachHang != null) {
                em.remove(khachHang);
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

    public List<KhachHang> getAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT k FROM KhachHang k", KhachHang.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<KhachHang> getByMonthAndYear(int month, int year) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT k FROM KhachHang k WHERE MONTH(k.ngayLapTaiKhoan) = :month AND YEAR(k.ngayLapTaiKhoan) = :year",
                            KhachHang.class)
                    .setParameter("month", month)
                    .setParameter("year", year)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<KhachHang> getByYear(int year) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT k FROM KhachHang k WHERE YEAR(k.ngayLapTaiKhoan) = :year", KhachHang.class)
                    .setParameter("year", year)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}