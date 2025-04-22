package dao;

import entity.Voucher;
import interfaces.IVoucher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Voucher_DAO extends UnicastRemoteObject implements IVoucher {

    private EntityManager entityManager;

    public Voucher_DAO() throws Exception {
        super();
        this.entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

        // Phương thức tạo mới một đối tượng Voucher
        public Boolean create (Voucher voucher){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(voucher);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

        // Phương thức lấy tất cả các đối tượng Voucher
        public List<Voucher> getAllVoucher () {
        try {
            TypedQuery<Voucher> query = entityManager.createQuery("SELECT v FROM Voucher v", Voucher.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

        // Phương thức lấy một đối tượng Voucher theo mã
        public Voucher getVoucher (String maVoucher){
        try {
            TypedQuery<Voucher> query = entityManager.createQuery("SELECT v FROM Voucher v WHERE v.maVoucher = :maVoucher", Voucher.class);
            query.setParameter("maVoucher", maVoucher);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        // Phương thức cập nhật thông tin Voucher
        public boolean suaVoucher (String maVoucher, Voucher newVoucher){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Voucher existingVoucher = entityManager.find(Voucher.class, maVoucher);
            if (existingVoucher != null) {
                existingVoucher.setTenVoucher(newVoucher.getTenVoucher());
                existingVoucher.setMoTa(newVoucher.getMoTa());
                existingVoucher.setNgayApDung(newVoucher.getNgayApDung());
                existingVoucher.setNgayKetThuc(newVoucher.getNgayKetThuc());
                existingVoucher.setGiaGiam(newVoucher.getGiaGiam());
                entityManager.merge(existingVoucher);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

        // Phương thức xóa Voucher
        public boolean xoaVoucher (String maVoucher){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Voucher voucher = entityManager.find(Voucher.class, maVoucher);
            if (voucher != null) {
                entityManager.remove(voucher);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
