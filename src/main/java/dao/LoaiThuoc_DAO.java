package dao;

import entity.LoaiThuoc;
import interfaces.ILoaiThuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class LoaiThuoc_DAO implements ILoaiThuoc {
    private EntityManager em;

    public LoaiThuoc_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<LoaiThuoc> findById(String maLoaiThuoc) {
        return Optional.ofNullable(em.find(LoaiThuoc.class, maLoaiThuoc));
    }

    @Override
    public List<LoaiThuoc> findAll() {
        TypedQuery<LoaiThuoc> query = em.createQuery("SELECT lt FROM LoaiThuoc lt", LoaiThuoc.class);
        return query.getResultList();
    }

    @Override
    public boolean create(LoaiThuoc loaiThuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(loaiThuoc);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(LoaiThuoc loaiThuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(loaiThuoc);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String maLoaiThuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiThuoc loaiThuoc = em.find(LoaiThuoc.class, maLoaiThuoc);
            if (loaiThuoc != null) {
                em.remove(loaiThuoc);
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