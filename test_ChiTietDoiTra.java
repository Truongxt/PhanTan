package crud;

import dao.ChiTietDoiTra_DAO;
import entity.ChiTietDoiTra;
import entity.ChiTietDoiTraId;
import entity.HoaDonDoiTra;
import entity.Thuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.util.List;
import java.util.Random;

public class test_ChiTietDoiTra {

    public static void main(String[] args) {
        // Tạo EntityManager và EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Khởi tạo DAO và Faker
        ChiTietDoiTra_DAO chiTietDoiTraDAO = new ChiTietDoiTra_DAO(em);
        Faker faker = new Faker();
        Random random = new Random();

        // Lấy danh sách mã hóa đơn đổi trả và mã thuốc từ cơ sở dữ liệu
        List<String> maHoaDonDoiTraList = em.createQuery("SELECT hddt.maHoaDonDoiTra FROM HoaDonDoiTra hddt", String.class).getResultList();
        List<String> maThuocList = em.createQuery("SELECT t.maThuoc FROM Thuoc t", String.class).getResultList();

        // 1. Tạo mới một chi tiết hóa đơn đổi trả
        ChiTietDoiTra chiTietDoiTra = new ChiTietDoiTra();

        ChiTietDoiTraId chiTietDoiTraId = new ChiTietDoiTraId();
        chiTietDoiTraId.setMaHoaDonDoiTra(maHoaDonDoiTraList.get(random.nextInt(maHoaDonDoiTraList.size())));
        chiTietDoiTraId.setMaThuoc(maThuocList.get(random.nextInt(maThuocList.size())));

        chiTietDoiTra.setId(chiTietDoiTraId);

        chiTietDoiTra.setSoLuong(faker.number().numberBetween(1, 10));
        chiTietDoiTra.setDonGia(faker.number().randomDouble(2, 10000, 50000));

        HoaDonDoiTra hoaDonDoiTra = em.find(HoaDonDoiTra.class, chiTietDoiTraId.getMaHoaDonDoiTra());
        Thuoc thuoc = em.find(Thuoc.class, chiTietDoiTraId.getMaThuoc());

        // Gán HoaDonDoiTra và Thuoc vào chi tiết
        chiTietDoiTra.setMaHoaDonDoiTra(hoaDonDoiTra);
        chiTietDoiTra.setMaThuoc(thuoc);

        // Gọi DAO để thêm mới
        boolean createSuccess = chiTietDoiTraDAO.create(chiTietDoiTra);
        if (createSuccess) {
            System.out.println("Tạo mới chi tiết hóa đơn đổi trả thành công.");
        }

        // 2. Lấy tất cả chi tiết hóa đơn đổi trả
        List<ChiTietDoiTra> danhSachChiTietDoiTra = chiTietDoiTraDAO.getAll();
        System.out.println("Danh sách chi tiết hóa đơn đổi trả:");
        for (ChiTietDoiTra ctdt : danhSachChiTietDoiTra) {
            System.out.println(ctdt);
        }

        // 3. Cập nhật thông tin chi tiết hóa đơn đổi trả
        if (!danhSachChiTietDoiTra.isEmpty()) {
            ChiTietDoiTra chiTietCanSua = danhSachChiTietDoiTra.get(0);
            chiTietCanSua.setSoLuong(chiTietCanSua.getSoLuong() + 2);
            boolean updateSuccess = chiTietDoiTraDAO.update(chiTietCanSua);
            if (updateSuccess) {
                System.out.println("Cập nhật chi tiết hóa đơn đổi trả thành công.");
            }
        }

        em.close();
        emf.close();
    }
}
