package dao;

import entity.DonViTinh;
import interfaces.IDonViTinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DonViTinh_DAO implements IDonViTinh {

    private EntityManager em;

    public DonViTinh_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<DonViTinh> findById(String maDonViTinh) {
        return Optional.ofNullable(em.find(DonViTinh.class, maDonViTinh));
    }

    @Override
    public List<DonViTinh> findAll() {
        TypedQuery<DonViTinh> query = em.createQuery("SELECT dvt FROM DonViTinh dvt", DonViTinh.class);
        return query.getResultList();
    }

    @Override
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

    @Override
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

    @Override
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