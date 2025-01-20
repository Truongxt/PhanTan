package dao;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class NhanVien_DAO {

    private EntityManager em;

    public NhanVien_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<NhanVien> findById(String maNhanVien) {
        return Optional.ofNullable(em.find(NhanVien.class, maNhanVien));
    }

    public List<NhanVien> findByMa(String maNhanVien) {
        TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien LIKE :maNhanVien", NhanVien.class);
        query.setParameter("maNhanVien", "%" + maNhanVien + "%");
        return query.getResultList();
    }

    public List<NhanVien> findByTen(String tenNhanVien) {
        TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.tenNhanVien LIKE :tenNhanVien", NhanVien.class);
        query.setParameter("tenNhanVien", "%" + tenNhanVien + "%");
        return query.getResultList();
    }

    public List<NhanVien> findByTrangThai(boolean trangThai) {
        TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.trangThai = :trangThai", NhanVien.class);
        query.setParameter("trangThai", trangThai);
        return query.getResultList();
    }

    public List<NhanVien> findBySdt(String sdt) {
        TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.sdt LIKE :sdt", NhanVien.class);
        query.setParameter("sdt", "%" + sdt + "%");
        return query.getResultList();
    }

    public boolean create(NhanVien nhanVien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(NhanVien nhanVien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maNhanVien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            NhanVien nhanVien = em.find(NhanVien.class, maNhanVien);
            if (nhanVien != null) {
                em.remove(nhanVien);
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