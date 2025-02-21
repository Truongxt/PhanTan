package dao;

import entity.ChiTietBangKiemTien;
import entity.ChiTietBangKiemTienId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ChiTietBangKiemTien_DAO {

    private EntityManager em;

    public ChiTietBangKiemTien_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<ChiTietBangKiemTien> findById(ChiTietBangKiemTienId id) {
        return Optional.ofNullable(em.find(ChiTietBangKiemTien.class, id));
    }

    public List<ChiTietBangKiemTien> findAll() {
        TypedQuery<ChiTietBangKiemTien> query = em.createQuery("SELECT ctbkt FROM ChiTietBangKiemTien ctbkt", ChiTietBangKiemTien.class);
        return query.getResultList();
    }

    public boolean create(ChiTietBangKiemTien chiTietBangKiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(chiTietBangKiemTien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(ChiTietBangKiemTien chiTietBangKiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(chiTietBangKiemTien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(ChiTietBangKiemTienId id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietBangKiemTien chiTietBangKiemTien = em.find(ChiTietBangKiemTien.class, id);
            if (chiTietBangKiemTien != null) {
                em.remove(chiTietBangKiemTien);
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