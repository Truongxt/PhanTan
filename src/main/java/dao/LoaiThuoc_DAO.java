package dao;

import entity.LoaiThuoc;
import interfaces.ILoaiThuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LoaiThuoc_DAO extends UnicastRemoteObject implements ILoaiThuoc {

    private final EntityManagerFactory emf;

    public LoaiThuoc_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public Boolean create(LoaiThuoc loaiThuoc) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(loaiThuoc);
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
    public ArrayList<LoaiThuoc> getAllLoaiThuoc() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<LoaiThuoc> query = em.createQuery("SELECT l FROM LoaiThuoc l", LoaiThuoc.class);
            List<LoaiThuoc> list = query.getResultList();
            return new ArrayList<>(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public LoaiThuoc getLoaiThuoc(String maLoai) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(LoaiThuoc.class, maLoai);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateLoaiThuoc(String maLoai, LoaiThuoc newLoaiThuoc) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            LoaiThuoc loaiThuoc = em.find(LoaiThuoc.class, maLoai);
            if (loaiThuoc != null) {
                loaiThuoc.setTenLoai(newLoaiThuoc.getTenLoai());
                em.merge(loaiThuoc);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSize() {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(l) FROM LoaiThuoc l", Long.class).getSingleResult();
            return count.intValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<LoaiThuoc> searchByMaLoai(String maLoai) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<LoaiThuoc> query = em.createQuery(
                    "SELECT l FROM LoaiThuoc l WHERE l.maLoai LIKE :maLoai", LoaiThuoc.class);
            query.setParameter("maLoai", "%" + maLoai + "%");
            List<LoaiThuoc> list = query.getResultList();
            return new ArrayList<>(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<LoaiThuoc> searchByTenLoai(String tenLoai) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<LoaiThuoc> query = em.createQuery(
                    "SELECT l FROM LoaiThuoc l WHERE l.tenLoai LIKE :tenLoai", LoaiThuoc.class);
            query.setParameter("tenLoai", "%" + tenLoai + "%");
            List<LoaiThuoc> list = query.getResultList();
            return new ArrayList<>(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
