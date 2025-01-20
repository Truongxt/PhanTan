package dao;

import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonId;
import entity.Thuoc;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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

    public boolean delete(String maHoaDon, String maThuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietHoaDon chiTiet = em.find(ChiTietHoaDon.class, new ChiTietHoaDonId(maHoaDon, maThuoc));
            if (chiTiet != null) {
                em.remove(chiTiet);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public int getSize() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(cthd) FROM ChiTietHoaDon cthd", Long.class);
        return query.getSingleResult().intValue();
    }


}