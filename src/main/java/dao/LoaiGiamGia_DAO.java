package dao;

import entity.LoaiGiamGia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class LoaiGiamGia_DAO {

    private EntityManager em;

    public LoaiGiamGia_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<LoaiGiamGia> findById(String maLoaiGiamGia) {
        return Optional.ofNullable(em.find(LoaiGiamGia.class, maLoaiGiamGia));
    }

    public List<LoaiGiamGia> findByTen(String tenLoaiGiamGia) {
        TypedQuery<LoaiGiamGia> query = em.createQuery("SELECT lg FROM LoaiGiamGia lg WHERE lg.tenLoaiGiamGia LIKE :tenLoaiGiamGia", LoaiGiamGia.class);
        query.setParameter("tenLoaiGiamGia", "%" + tenLoaiGiamGia + "%");
        return query.getResultList();
    }

    public boolean create(LoaiGiamGia loaiGiamGia) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(loaiGiamGia);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(LoaiGiamGia loaiGiamGia) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(loaiGiamGia);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maLoaiGiamGia) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiGiamGia loaiGiamGia = em.find(LoaiGiamGia.class, maLoaiGiamGia);
            if (loaiGiamGia != null) {
                em.remove(loaiGiamGia);
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