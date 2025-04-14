package dao;

import entity.KhachHang;
import interfaces.IKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class KhachHang_DAO implements IKhachHang {

    private EntityManager em;

    public KhachHang_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<KhachHang> findById(String maKhachHang) {
        return Optional.ofNullable(em.find(KhachHang.class, maKhachHang));
    }

    @Override
    public List<KhachHang> findByTen(String tenKhachHang) {
        TypedQuery<KhachHang> query = em.createQuery("SELECT kh FROM KhachHang kh WHERE kh.tenKhachHang LIKE :tenKhachHang", KhachHang.class);
        query.setParameter("tenKhachHang", "%" + tenKhachHang + "%");
        return query.getResultList();
    }

    @Override
    public List<KhachHang> findBySdt(String sdt) {
        TypedQuery<KhachHang> query = em.createQuery("SELECT kh FROM KhachHang kh WHERE kh.sdt LIKE :sdt", KhachHang.class);
        query.setParameter("sdt", "%" + sdt + "%");
        return query.getResultList();
    }

    @Override
    public boolean create(KhachHang khachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(KhachHang khachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String maKhachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KhachHang khachHang = em.find(KhachHang.class, maKhachHang);
            if (khachHang != null) {
                em.remove(khachHang);
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