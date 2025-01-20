// src/main/java/dao/HoaDon_DAO.java
package dao;

import entity.HoaDon;
import jakarta.persistence.EntityManager;
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

    public List<HoaDon> getAll() {
        TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd", HoaDon.class);
        return query.getResultList();
    }

    public boolean create(HoaDon hoaDon) {
        try {
            em.persist(hoaDon);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(HoaDon hoaDon) {
        try {
            em.merge(hoaDon);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}