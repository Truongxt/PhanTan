package dao;

import entity.HoaDonDoiTra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class HoaDonDoiTra_DAO {

    private EntityManager em;

    public HoaDonDoiTra_DAO(EntityManager em) {
        this.em = em;
    }

    // Lấy tất cả hóa đơn đổi trả
    public List<HoaDonDoiTra> getAll() {
        TypedQuery<HoaDonDoiTra> query = em.createQuery("SELECT hddt FROM HoaDonDoiTra hddt", HoaDonDoiTra.class);
        return query.getResultList();
    }

    // Thêm mới hóa đơn đổi trả
    public boolean create(HoaDonDoiTra hoaDonDoiTra) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hoaDonDoiTra);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    // Cập nhật hóa đơn đổi trả
    public boolean update(HoaDonDoiTra hoaDonDoiTra) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hoaDonDoiTra);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }


    // Lấy thông tin 1 hóa đơn đổi trả theo mã
    public Optional<HoaDonDoiTra> getOne(String maHoaDonDoiTra) {
        TypedQuery<HoaDonDoiTra> query = em.createQuery("SELECT hddt FROM HoaDonDoiTra hddt WHERE hddt.maHoaDonDoiTra = :maHoaDonDoiTra", HoaDonDoiTra.class);
        query.setParameter("maHoaDonDoiTra", maHoaDonDoiTra);
        return query.getResultStream().findFirst();
    }
}
