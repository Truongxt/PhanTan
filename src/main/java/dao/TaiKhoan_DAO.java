package dao;

import entity.TaiKhoan;
import interfaces.ITaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class TaiKhoan_DAO implements ITaiKhoan {

    private EntityManager em;

    public TaiKhoan_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<TaiKhoan> findById(String maTaiKhoan) {
        return Optional.ofNullable(em.find(TaiKhoan.class, maTaiKhoan));
    }

    @Override
    public List<TaiKhoan> findByTenTaiKhoan(String tenTaiKhoan) {
        TypedQuery<TaiKhoan> query = em.createQuery("SELECT tk FROM TaiKhoan tk WHERE tk.tenTaiKhoan LIKE :tenTaiKhoan", TaiKhoan.class);
        query.setParameter("tenTaiKhoan", "%" + tenTaiKhoan + "%");
        return query.getResultList();
    }

    @Override
    public boolean create(TaiKhoan taiKhoan) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(taiKhoan);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(TaiKhoan taiKhoan) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(taiKhoan);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String maTaiKhoan) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            TaiKhoan taiKhoan = em.find(TaiKhoan.class, maTaiKhoan);
            if (taiKhoan != null) {
                em.remove(taiKhoan);
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