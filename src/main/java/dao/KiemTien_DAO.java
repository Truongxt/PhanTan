package dao;

import entity.KiemTien;
import interfaces.IKiemTien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class KiemTien_DAO implements IKiemTien {

    private EntityManager em;

    public KiemTien_DAO(EntityManager em) {
        this.em = em;
    }

    public boolean create(KiemTien kiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(kiemTien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public KiemTien getKiemTienTheoMa(String maKiemTien) {
        try {
            return em.find(KiemTien.class, maKiemTien);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KiemTien> getAllKiemTien() {
        try {
            return em.createQuery("SELECT kt FROM KiemTien kt", KiemTien.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(KiemTien kiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(kiemTien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maKiemTien) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KiemTien kiemTien = em.find(KiemTien.class, maKiemTien);
            if (kiemTien != null) {
                em.remove(kiemTien);
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