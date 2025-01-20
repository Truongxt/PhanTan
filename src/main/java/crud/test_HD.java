// src/main/java/crud/test_HD.java
package crud;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class test_HD {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        ChiTietHoaDon_DAO cthdDAO = new ChiTietHoaDon_DAO(em);
        HoaDon_DAO hdDAO = new HoaDon_DAO(em);

        // Lấy và in chi tiết HoaDon hiện có
        int size = cthdDAO.getSize();
        HoaDon hd = hdDAO.getAll().get(2);
        System.out.println("Số lượng hóa đơn là: " + size);
        System.out.println("Hóa đơn có mã hóa đơn là " + hd.getMaHD() + " là: " + hdDAO.findById(hd.getMaHD()));
        System.out.println("Danh sách chi tiết hóa đơn có mã hóa đơn là " + hd.getMaHD() + " là: " + cthdDAO.getChiTietHoaDon(hd.getMaHD()));

//        // Tạo một HoaDon mới
//        HoaDon newHoaDon = new HoaDon();
//        newHoaDon.setMaHD("HD-12345678");
//        newHoaDon.setNgayLap(java.time.LocalDate.now());
//        newHoaDon.setTongTien(100000.0);
//
//        try {
//            tr.begin();
//            hdDAO.create(newHoaDon);
//            tr.commit();
//            System.out.println("Hóa đơn mới đã được tạo: " + newHoaDon);
//        } catch (Exception e) {
//            tr.rollback();
//            e.printStackTrace();
//        }

        // Cập nhật HoaDon hiện có
        hd.setTongTien(200000.0); // Ví dụ cập nhật

        try {
            tr.begin();
            hdDAO.update(hd);
            tr.commit();
            System.out.println("Hóa đơn đã được cập nhật: " + hd);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }



        // Đóng EntityManager và EntityManagerFactory
        em.close();
        emf.close();
    }
}