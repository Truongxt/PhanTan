package dao;

import interfaces.IThuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import entity.Thuoc;

import java.util.List;

@AllArgsConstructor
public class Thuoc_DAO implements IThuoc {

    private EntityManager em;

    @Override
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

    @Override
    public Thuoc getThuocTheoMa(String mt) {
        try {
            return em.find(Thuoc.class, mt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Thuoc> getAllThuoc() {
        try {
            return em.createQuery("SELECT t FROM Thuoc t", Thuoc.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
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

    @Override
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