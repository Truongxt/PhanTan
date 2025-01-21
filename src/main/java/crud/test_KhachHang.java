// src/main/java/crud/test_KhachHang.java
package crud;

import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class test_KhachHang {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();

            KhachHang_DAO khachHangDAO = new KhachHang_DAO(em);

            // Create a new KhachHang
            KhachHang newKhachHang = new KhachHang();
            newKhachHang.setMaKhachHang("KH-001");
            newKhachHang.setTenKhachHang("Nguyen Van A");
            newKhachHang.setDiaChi("123 Le Loi");

            EntityTransaction tr = em.getTransaction();

            khachHangDAO.create(newKhachHang);

            System.out.println("KhachHang moi da duoc tao: " + newKhachHang);

            // Update the KhachHang
            newKhachHang.setTenKhachHang("Nguyen Van B");
            newKhachHang.setDiaChi("456 Tran Phu");


            khachHangDAO.update(newKhachHang);

            System.out.println("KhachHang da duoc cap nhat: " + newKhachHang);

            // Delete the KhachHang

            khachHangDAO.delete(newKhachHang.getMaKhachHang());
            
            System.out.println("KhachHang da duoc xoa: " + newKhachHang.getMaKhachHang());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}