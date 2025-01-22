// src/main/java/crud/test_LoaiThuoc.java
package crud;

import dao.LoaiThuoc_DAO;
import entity.LoaiThuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

public class test_LoaiThuoc {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        LoaiThuoc_DAO loaiThuocDAO = new LoaiThuoc_DAO(em);
        Faker faker = new Faker();
        // Create a new LoaiThuoc
        LoaiThuoc newLoaiThuoc = new LoaiThuoc();
        String maThuoc= faker.bothify("LT-###"); ;
        newLoaiThuoc.setMaLoai("LT-001");
        newLoaiThuoc.setTenLoai("Thuoc Giam Dau");


            loaiThuocDAO.create(newLoaiThuoc);


        // Update the LoaiThuoc
        newLoaiThuoc.setTenLoai("Thuoc Giam Dau Cap Tinh");


            loaiThuocDAO.update(newLoaiThuoc);

            System.out.println("Loai thuoc da duoc cap nhat: " + newLoaiThuoc);


        // Delete the LoaiThuoc

            loaiThuocDAO.delete(newLoaiThuoc.getMaLoai());

            System.out.println("Loai thuoc da duoc xoa: " + newLoaiThuoc.getMaLoai());
        String maLoai = "LT-001"; // Replace with the actual LoaiThuoc ID


        LoaiThuoc loaiThuoc1 = loaiThuocDAO.findById(maLoai).orElse(null);
        if (loaiThuoc1 != null) {
            System.out.println("Loai thuoc tim thay: " + loaiThuoc1);
        } else {
            System.out.println("Khong tim thay loai thuoc voi ma: " + maLoai);
        }

        em.close();
        emf.close();
    }
}