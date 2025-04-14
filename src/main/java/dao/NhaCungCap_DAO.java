package dao;

import entity.NhaCungCap;
import interfaces.INhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class NhaCungCap_DAO implements INhaCungCap {

    private EntityManager em;

    public NhaCungCap_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<NhaCungCap> findById(String maNhaCungCap) {
        return Optional.ofNullable(em.find(NhaCungCap.class, maNhaCungCap));
    }

    @Override
    public List<NhaCungCap> findByTen(String tenNhaCungCap) {
        TypedQuery<NhaCungCap> query = em.createQuery("SELECT ncc FROM NhaCungCap ncc WHERE ncc.tenNCC LIKE :tenNhaCungCap", NhaCungCap.class);
        query.setParameter("tenNhaCungCap", "%" + tenNhaCungCap + "%");
        return query.getResultList();
    }

    @Override
    public boolean create(NhaCungCap nhaCungCap) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(nhaCungCap);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(NhaCungCap nhaCungCap) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(nhaCungCap);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String maNhaCungCap) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            NhaCungCap nhaCungCap = em.find(NhaCungCap.class, maNhaCungCap);
            if (nhaCungCap != null) {
                em.remove(nhaCungCap);
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