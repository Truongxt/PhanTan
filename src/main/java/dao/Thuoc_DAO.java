package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import entity.Thuoc;

import java.util.List;

@AllArgsConstructor
public class Thuoc_DAO {

    private EntityManager em;

    public boolean create(Thuoc thuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(thuoc);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public Thuoc getThuocTheoMa(String mt) {
        try {
            return em.find(Thuoc.class, mt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Thuoc> getAllThuoc() {
        try {
            return em.createQuery("SELECT t FROM Thuoc t", Thuoc.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Thuoc thuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(thuoc);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maThuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Thuoc thuoc = em.find(Thuoc.class, maThuoc);
            if (thuoc != null) {
                em.remove(thuoc);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
}