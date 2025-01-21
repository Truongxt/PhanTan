// src/main/java/crud/test_HD.java
package crud;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonId;
import entity.HoaDon;
import entity.KhachHang;
import entity.Thuoc;
import jakarta.persistence.*;
import net.datafaker.Faker;

import java.util.List;

public class test_HD {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        ChiTietHoaDon_DAO cthdDAO = new ChiTietHoaDon_DAO(em);
        HoaDon_DAO hdDAO = new HoaDon_DAO(em);
        Faker faker = new Faker();
        java.util.Random rd = new java.util.Random();

        // Lấy và in chi tiết HoaDon hiện có
        int size = cthdDAO.getSize();
        HoaDon hd = hdDAO.getAll().get(2);
        System.out.println("Số lượng hóa đơn là: " + size);
        System.out.println("Hóa đơn có mã hóa đơn là " + hd.getMaHD() + " là: " + hdDAO.findById(hd.getMaHD()));
        System.out.println("Danh sách chi tiết hóa đơn có mã hóa đơn là " + hd.getMaHD() + " là: " + cthdDAO.getChiTietHoaDon(hd.getMaHD()));

        // Tạo một HoaDon mới
        HoaDon newHoaDon = new HoaDon();
        String maHD = faker.bothify("HD-########");
        newHoaDon.setMaHD(maHD);
        newHoaDon.setNgayLap(java.time.LocalDate.now());
        newHoaDon.setTongTien(100000.0);

        // Thiết lập một thực thể KhachHang hợp lệ
        KhachHang khachHang = em.find(KhachHang.class, "KH-12345678"); // Giả sử KhachHang này đã tồn tại
        newHoaDon.setMaKH(khachHang);

        try {
            tr.begin();
            hdDAO.create(newHoaDon);
            tr.commit();
            System.out.println("Hóa đơn mới đã được tạo: " + newHoaDon);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }

        // Tạo một ChiTietHoaDon mới
        TypedQuery<String> query1 = em.createQuery("SELECT n.maThuoc FROM Thuoc n", String.class);
        List<String> maThuoc = query1.getResultList();
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();

        chiTietHoaDon.setDonGia(faker.number().randomDouble(2, 1000, 100000));
        chiTietHoaDon.setSoLuong(faker.number().numberBetween(1, 100));
        String maThuoc1 = maThuoc.get(rd.nextInt(maThuoc.size()));

        chiTietHoaDon.setMaHD(newHoaDon); // Đảm bảo maHD được thiết lập
        chiTietHoaDon.setMaThuoc(em.find(Thuoc.class, maThuoc1));
        ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId(maHD, maThuoc1);

        chiTietHoaDon.setId(chiTietHoaDonId);
        try {
            tr.begin();
            em.persist(chiTietHoaDon);
            tr.commit();
            System.out.println("Chi tiết hóa đơn mới đã được tạo: " + chiTietHoaDon);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }

        // Cập nhật giá của một hóa đơn
        if (newHoaDon != null) {
            // Update the price
            newHoaDon.setTongTien(200000.0); // Set the new price

            try {
                tr.begin();
                hdDAO.update(newHoaDon);
                tr.commit();
                System.out.println("Hóa đơn đã được cập nhật: " + newHoaDon);
            } catch (Exception e) {
                tr.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Hóa đơn không tồn tại.");
        }

        em.close();
        emf.close();
    }
}