// src/main/java/crud/test_NhanVien.java
package crud;

import dao.NhanVien_DAO;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

public class test_NhanVien {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        NhanVien_DAO nhanVienDAO = new NhanVien_DAO(em);
        Faker faker = new Faker();

        // Create a new NhanVien
        NhanVien newNhanVien = new NhanVien();
        String maNhanVien = faker.bothify("NV-###");
        newNhanVien.setMaNhanVien(maNhanVien);
        newNhanVien.setTenNhanVien("Nguyen Van A");
        newNhanVien.setSdt("0123456789");
        newNhanVien.setCccd("13156466516");
        newNhanVien.setDiaChi("Ha No");
        newNhanVien.setEmail(faker.internet().emailAddress());
        newNhanVien.setNgayVaoLam(faker.date().birthdayLocalDate());

        nhanVienDAO.create(newNhanVien);

        System.out.println("Nhan vien moi da duoc tao: " + newNhanVien);

        // Update the NhanVien
        newNhanVien.setTenNhanVien("Nguyen Van B");


        nhanVienDAO.update(newNhanVien);

        System.out.println("Nhan vien da duoc cap nhat: " + newNhanVien);

        // Delete the NhanVien

        nhanVienDAO.delete(newNhanVien.getMaNhanVien());

        System.out.println("Nhan vien da duoc xoa: " + newNhanVien.getMaNhanVien());

        // Find the NhanVien
        String maNV = "NV-001"; // Replace with the actual NhanVien ID
        NhanVien nhanVien = nhanVienDAO.findById(maNV).orElse(null);
        if (nhanVien != null) {
            System.out.println("Nhan vien tim thay: " + nhanVien);
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }

        em.close();
        emf.close();
    }
}