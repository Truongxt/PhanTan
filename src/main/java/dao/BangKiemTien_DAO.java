package dao;

import entity.BangKiemTien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BangKiemTien_DAO {

    private EntityManager em;

    public BangKiemTien_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<BangKiemTien> findById(String id) {
        return Optional.ofNullable(em.find(BangKiemTien.class, id));
    }

    public List<BangKiemTien> findAll() {
        TypedQuery<BangKiemTien> query = em.createQuery("SELECT b FROM BangKiemTien b", BangKiemTien.class);
        return query.getResultList();
    }

    public boolean create(BangKiemTien bangKiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(bangKiemTien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(BangKiemTien bangKiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(bangKiemTien);
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
            BangKiemTien bangKiemTien = em.find(BangKiemTien.class, id);
            if (bangKiemTien != null) {
                em.remove(bangKiemTien);
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