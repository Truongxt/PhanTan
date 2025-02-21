package dao;

import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonId;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ChiTietHoaDon_DAO {

    private EntityManager em;

    public ChiTietHoaDon_DAO(EntityManager em) {
        this.em = em;
    }

    public boolean create(ChiTietHoaDon chiTiet) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(chiTiet);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        TypedQuery<ChiTietHoaDon> query = em.createQuery("SELECT cthd FROM ChiTietHoaDon cthd", ChiTietHoaDon.class);
        return query.getResultList();
    }

    public boolean update(ChiTietHoaDon chiTiet) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(chiTiet);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }


    public int getSize() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(*) FROM ChiTietHoaDon cthd", Long.class);
        return query.getSingleResult().intValue();
    }

    public List<ChiTietHoaDon> getChiTietHoaDon(String maHoaDon) {
        TypedQuery<ChiTietHoaDon> query = em.createQuery("SELECT cthd FROM ChiTietHoaDon cthd WHERE cthd.maHD.id = :maHoaDon", ChiTietHoaDon.class);
        query.setParameter("maHoaDon", maHoaDon);
        return query.getResultList();
    }
}