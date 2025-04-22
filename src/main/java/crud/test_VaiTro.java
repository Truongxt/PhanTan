//// src/main/java/crud/test_VaiTro.java
//package crud;
//
//import dao.VaiTro_DAO;
//import entity.VaiTro;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//import net.datafaker.Faker;
//
//import java.rmi.RemoteException;
//
//public class test_VaiTro {
//
//    public static void main(String[] args) throws RemoteException {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//        EntityManager em = emf.createEntityManager();
//
//        VaiTro_DAO vaiTroDAO = new VaiTro_DAO(em);
//        Faker faker = new Faker();
//
//        // Create a new VaiTro
//        VaiTro newVaiTro = new VaiTro();
//        String maVaiTro = faker.bothify("VT-###");
//        newVaiTro.setMaVaiTro(maVaiTro);
//        newVaiTro.setTenVaiTro("Admin");
//
//   //     vaiTroDAO.create(newVaiTro);
//        System.out.println("Vai tro moi da duoc tao: " + newVaiTro);
//
//        // Update the VaiTro
//        newVaiTro.setTenVaiTro("User");
//   //     vaiTroDAO.update(newVaiTro);
//        System.out.println("Vai tro da duoc cap nhat: " + newVaiTro);
//
//        // Delete the VaiTro
//  //      vaiTroDAO.delete(newVaiTro.getMaVaiTro());
//        System.out.println("Vai tro da duoc xoa: " + newVaiTro.getMaVaiTro());
//
//        // Find the VaiTro
//        String maVT = "VT-001"; // Replace with the actual VaiTro ID
////        VaiTro vaiTro = vaiTroDAO.findById(maVT).orElse(null);
////        if (vaiTro != null) {
////            System.out.println("Vai tro tim thay: " + vaiTro);
////        } else {
////            System.out.println("Khong tim thay vai tro voi ma: " + maVT);
////        }
//
//        em.close();
//        emf.close();
//    }
//}