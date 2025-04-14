
package dao;

import entity.HoaDon;
import interfaces.IHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class HoaDon_DAO implements IHoaDon {

    private EntityManager em;

    public HoaDon_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<HoaDon> findById(String maHoaDon) {
        return Optional.ofNullable(em.find(HoaDon.class, maHoaDon));
    }

    @Override
    public List<HoaDon> getAll() {
        TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd", HoaDon.class);
        return query.getResultList();
    }

    @Override
    public boolean create(HoaDon hoaDon) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean update(HoaDon hoaDon) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return false;
    }

}