// src/main/java/crud/test_XuatXu.java
package crud;

import dao.XuatXu_DAO;
import entity.XuatXu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

public class test_XuatXu {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        XuatXu_DAO xuatXuDAO = new XuatXu_DAO(em);
        Faker faker = new Faker();

        // Create a new XuatXu
        XuatXu newXuatXu = new XuatXu();
        String maXuatXu = faker.bothify("XX-###");
        newXuatXu.setMaXuatXu(maXuatXu);
        newXuatXu.setTenXuatXu("Vietnam");


        xuatXuDAO.create(newXuatXu);

        System.out.println("Xuat xu moi da duoc tao: " + newXuatXu);

        // Update the XuatXu
        newXuatXu.setTenXuatXu("USA");


        xuatXuDAO.update(newXuatXu);

        System.out.println("Xuat xu da duoc cap nhat: " + newXuatXu);

        // Delete the XuatXu

        xuatXuDAO.delete(newXuatXu.getMaXuatXu());

        System.out.println("Xuat xu da duoc xoa: " + newXuatXu.getMaXuatXu());

        // Find the XuatXu
        String maXX = "XX-00524330"; // Replace with the actual XuatXu ID
        XuatXu xuatXu = xuatXuDAO.findById(maXX);
        if (xuatXu != null) {
            System.out.println("Xuat xu tim thay: " + xuatXu);
        } else {
            System.out.println("Khong tim thay xuat xu voi ma: " + maXX);
        }

        em.close();
        emf.close();
    }
}