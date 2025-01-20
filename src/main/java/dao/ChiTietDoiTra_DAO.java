package dao;

import entity.ChiTietDoiTra;
import entity.ChiTietDoiTraId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ChiTietDoiTra_DAO implements DAOBase<ChiTietDoiTra> {

    private EntityManager em;

    public ChiTietDoiTra_DAO(EntityManager em) {
        this.em = em;
    }

    public List<ChiTietDoiTra> getAll() {
        TypedQuery<ChiTietDoiTra> query = em.createQuery("SELECT ctdt FROM ChiTietDoiTra ctdt", ChiTietDoiTra.class);
        return query.getResultList();
    }

    public boolean create(ChiTietDoiTra chiTietDoiTra) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(chiTietDoiTra);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(ChiTietDoiTra chiTietDoiTra) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(chiTietDoiTra);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maHDDT, String maThuoc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Optional<ChiTietDoiTra> chiTietDoiTra = getOne(maHDDT, maThuoc);
            if (chiTietDoiTra.isPresent()) {
                em.remove(chiTietDoiTra.get());
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public Optional<ChiTietDoiTra> getOne(String maHDDT, String maThuoc) {
        TypedQuery<ChiTietDoiTra> query = em.createQuery("SELECT ctdt FROM ChiTietDoiTra ctdt WHERE ctdt.id.maHoaDonDoiTra = :maHDDT AND ctdt.id.maThuoc = :maThuoc", ChiTietDoiTra.class);
        query.setParameter("maHDDT", maHDDT);
        query.setParameter("maThuoc", maThuoc);
        return query.getResultStream().findFirst();
    }
}