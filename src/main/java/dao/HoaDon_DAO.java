package dao;

import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class HoaDon_DAO {

    private EntityManager em;

    public HoaDon_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<HoaDon> findById(String maHoaDon) {
        return Optional.ofNullable(em.find(HoaDon.class, maHoaDon));
    }

    public List<HoaDon> findAll() {
        TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd", HoaDon.class);
        return query.getResultList();
    }

    public boolean create(HoaDon hoaDon) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(HoaDon hoaDon) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maHoaDon) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            HoaDon hoaDon = em.find(HoaDon.class, maHoaDon);
            if (hoaDon != null) {
                em.remove(hoaDon);
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