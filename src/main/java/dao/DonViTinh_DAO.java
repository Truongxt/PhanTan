package dao;

import entity.DonViTinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DonViTinh_DAO {

    private EntityManager em;

    public DonViTinh_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<DonViTinh> findById(String maDonViTinh) {
        return Optional.ofNullable(em.find(DonViTinh.class, maDonViTinh));
    }

    public List<DonViTinh> findAll() {
        TypedQuery<DonViTinh> query = em.createQuery("SELECT dvt FROM DonViTinh dvt", DonViTinh.class);
        return query.getResultList();
    }

    public boolean create(DonViTinh donViTinh) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(donViTinh);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(DonViTinh donViTinh) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(donViTinh);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maDonViTinh) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            DonViTinh donViTinh = em.find(DonViTinh.class, maDonViTinh);
            if (donViTinh != null) {
                em.remove(donViTinh);
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