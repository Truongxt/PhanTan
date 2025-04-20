package dao;

import entity.KiemTien;
import interfaces.IKiemTien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class KiemTien_DAO extends UnicastRemoteObject implements IKiemTien {

    private final EntityManagerFactory emf;

    public KiemTien_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public boolean create(KiemTien kiemTien) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(kiemTien);
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
    public KiemTien getKiemTienTheoMa(String maKiemTien) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(KiemTien.class, maKiemTien);
        } finally {
            em.close();
        }
    }

    @Override
    public List<KiemTien> getAllKiemTien() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            List<KiemTien> result = em.createQuery("SELECT k FROM KiemTien k", KiemTien.class).getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(KiemTien kiemTien) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            KiemTien existingEntity = em.find(KiemTien.class, kiemTien.getMaBangKiemTien());
            if (existingEntity != null) {
                existingEntity.setGiaTri(kiemTien.getGiaTri());
                existingEntity.setSoLuong(kiemTien.getSoLuong());
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
    public boolean delete(String maKiemTien) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            KiemTien kiemTien = em.find(KiemTien.class, maKiemTien);
            if (kiemTien != null) {
                em.remove(kiemTien);
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

//    public double getTongTien(String maBangKiemTien) throws Exception {
//        EntityManager em = emf.createEntityManager();
//        try {
//            Double total = em.createQuery(
//                            "SELECT SUM(k.giaTri * k.soLuong) FROM KiemTien k WHERE k.maBangKiemTien.maBangKiemTien = :maBangKiemTien",
//                            Double.class)
//                    .setParameter("maBangKiemTien", maBangKiemTien)
//                    .getSingleResult();
//            return total != null ? total : 0.0;
//        } finally {
//            em.close();
//        }
//    }
}