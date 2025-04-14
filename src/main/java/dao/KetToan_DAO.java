package dao;

import entity.KetToan;
import interfaces.IKetToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class KetToan_DAO implements IKetToan {

    private EntityManager em;

    public KetToan_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<KetToan> findById(String maKetToan) {
        return Optional.ofNullable(em.find(KetToan.class, maKetToan));
    }

    @Override
    public List<KetToan> findAll() {
        return em.createQuery("SELECT kt FROM KetToan kt", KetToan.class).getResultList();
    }

    @Override
    public boolean create(KetToan ketToan) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(ketToan);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(KetToan ketToan) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(ketToan);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String maKetToan) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KetToan ketToan = em.find(KetToan.class, maKetToan);
            if (ketToan != null) {
                em.remove(ketToan);
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