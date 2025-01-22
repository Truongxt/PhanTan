// src/main/java/crud/test_KhachHang.java
package crud;

import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

public class test_KhachHang {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Faker faker = new Faker();

            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();

            KhachHang_DAO khachHangDAO = new KhachHang_DAO(em);

            // Create a new KhachHang
            KhachHang newKhachHang = new KhachHang();
            String maKH=faker.bothify("KH-########");
            newKhachHang.setMaKhachHang("maKH");
            newKhachHang.setTenKhachHang("Nguyen Van A");
            newKhachHang.setDiaChi("123 Le Loi");
            newKhachHang.setSdt("0123456789");


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



        // Find and print a customer by its ID
        String maKhachHang = "KH-00908051"; // Replace with the actual customer ID
        KhachHang khachHang1 = khachHangDAO.findById(maKhachHang).orElse(null);
        if (khachHang1 != null) {
            System.out.println("Khách hàng tìm thấy: " + khachHang1);
        } else {
            System.out.println("Không tìm thấy khách hàng với mã: " + maKhachHang);
        }
        em.close();
        emf.close();


    }
}