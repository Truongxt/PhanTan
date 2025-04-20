package dao;

import entity.XuatXu;
import interfaces.IXuatXu;

import jakarta.persistence.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class XuatXu_DAO implements IXuatXu {

    private final EntityManagerFactory emf;

    public XuatXu_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public boolean create(XuatXu xx) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(xx);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<XuatXu> getAllXuatXu() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<XuatXu> query = em.createQuery("SELECT x FROM XuatXu x", XuatXu.class);
            List<XuatXu> result = query.getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public XuatXu getXuatXuById(String maXuatXu) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(XuatXu.class, maXuatXu);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateXuatXu(String maXuatXu, XuatXu newXuatXu) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            XuatXu existing = em.find(XuatXu.class, maXuatXu);
            if (existing != null) {
                existing.setTenXuatXu(newXuatXu.getTenXuatXu());
                em.merge(existing);
                tx.commit();
                return true;
            }
            tx.rollback();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean deleteXuatXu(String maXuatXu) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            XuatXu xx = em.find(XuatXu.class, maXuatXu);
            if (xx != null) {
                em.remove(xx);
                tx.commit();
                return true;
            }
            tx.rollback();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public int getSize() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(x) FROM XuatXu x", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
