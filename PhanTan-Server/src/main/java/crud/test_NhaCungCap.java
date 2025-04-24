//// src/main/java/crud/test_NhaCungCap.java
//package crud;
//
//import dao.NhaCungCap_DAO;
//import entity.NhaCungCap;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//import net.datafaker.Faker;
//
//public class test_NhaCungCap {
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//        EntityManager em = emf.createEntityManager();
//
//
//        NhaCungCap_DAO nhaCungCapDAO = new NhaCungCap_DAO(em);
//        Faker faker = new Faker();
//
//        // Create a new NhaCungCap
//        NhaCungCap newNhaCungCap = new NhaCungCap();
//        String maNCC = faker.bothify("NCC-###");
//        newNhaCungCap.setMaNCC(maNCC);
//        newNhaCungCap.setTenNCC("Cong ty ABC");
//        newNhaCungCap.setDiaChi("Ha Noi");
//        newNhaCungCap.setSdt("0123456789");
//        newNhaCungCap.setEmail(faker.internet().emailAddress());
//
//
//        nhaCungCapDAO.create(newNhaCungCap);
//
//        System.out.println("Nha cung cap moi da duoc tao: " + newNhaCungCap);
//
//        // Update the NhaCungCap
//        newNhaCungCap.setTenNCC("Cong ty XYZ");
//
//
//        nhaCungCapDAO.update(newNhaCungCap);
//
//        System.out.println("Nha cung cap da duoc cap nhat: " + newNhaCungCap);
//
//        // Delete the NhaCungCap
//
//        nhaCungCapDAO.delete(newNhaCungCap.getMaNCC());
//
//        System.out.println("Nha cung cap da duoc xoa: " + newNhaCungCap.getMaNCC());
//
//        // Find the NhaCungCap
//        String maNCCToFind = "NCC-001"; // Replace with the actual NhaCungCap ID
//        NhaCungCap nhaCungCap = nhaCungCapDAO.findById(maNCCToFind).orElse(null);
//        if (nhaCungCap != null) {
//            System.out.println("Nha cung cap tim thay: " + nhaCungCap);
//        } else {
//            System.out.println("Khong tim thay nha cung cap voi ma: " + maNCCToFind);
//        }
//
//        em.close();
//        emf.close();
//    }
//}