package dao;

import entity.VaiTro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class VaiTro_DAO {

    private EntityManager em;

    public VaiTro_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<VaiTro> findById(String maVaiTro) {
        return Optional.ofNullable(em.find(VaiTro.class, maVaiTro));
    }

    public List<VaiTro> findAll() {
        TypedQuery<VaiTro> query = em.createQuery("SELECT vt FROM VaiTro vt", VaiTro.class);
        return query.getResultList();
    }

    public boolean create(VaiTro vaiTro) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(vaiTro);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(VaiTro vaiTro) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(vaiTro);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maVaiTro) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            VaiTro vaiTro = em.find(VaiTro.class, maVaiTro);
            if (vaiTro != null) {
                em.remove(vaiTro);
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