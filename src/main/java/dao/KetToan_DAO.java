package dao;

import entity.KetToan;
import interfaces.IKetToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KetToan_DAO extends UnicastRemoteObject implements IKetToan {

    private final EntityManagerFactory emf;

    public KetToan_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }



    @Override
    public Optional<KetToan> findById(String maKetToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            KetToan ketToan = em.find(KetToan.class, maKetToan);
            return Optional.ofNullable(ketToan);
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
            KetToan existingKetToan = em.find(KetToan.class, ketToan.getMaKetToan());
            if (existingKetToan != null) {
                existingKetToan.setNgayBatDau(ketToan.getNgayBatDau());
                existingKetToan.setNgayKetThuc(ketToan.getNgayKetThuc());
                existingKetToan.setBangKiemTien(ketToan.getBangKiemTien());
                existingKetToan.setHoaDons(ketToan.getHoaDons());
                em.merge(existingKetToan);
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

    public List<KetToan> filterByDateRange(java.util.Date startDate, java.util.Date endDate) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT k FROM KetToan k WHERE k.ngayBatDau >= :startDate AND k.ngayKetThuc <= :endDate", KetToan.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public double calculateTotalRevenue(String maKetToan) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Double total = em.createQuery(
                            "SELECT SUM(h.tongTien) FROM HoaDon h WHERE h.ketToan.maKetToan = :maKetToan", Double.class)
                    .setParameter("maKetToan", maKetToan)
                    .getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }
}