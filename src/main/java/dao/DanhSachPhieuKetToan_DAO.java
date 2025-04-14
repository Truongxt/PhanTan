package dao;

import entity.KetToan;
import interfaces.IDanhSachPhieuKetToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DanhSachPhieuKetToan_DAO implements IDanhSachPhieuKetToan {

    private EntityManager em;

    public DanhSachPhieuKetToan_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<KetToan> findById(String id) {
        return Optional.ofNullable(em.find(KetToan.class, id));
    }

    public List<KetToan> findAll() {
        TypedQuery<KetToan> query = em.createQuery("SELECT kt FROM KetToan kt", KetToan.class);
        return query.getResultList();
    }

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

    public boolean delete(String id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KetToan ketToan = em.find(KetToan.class, id);
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