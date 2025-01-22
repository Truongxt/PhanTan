package crud;
import dao.HoaDonDoiTra_DAO;
import entity.HoaDonDoiTra;
import entity.HoaDon;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class test_HoaDonDoiTra {

    public static void main(String[] args) {
        // Tạo EntityManager và EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Khởi tạo DAO và Faker
        HoaDonDoiTra_DAO hoaDonDoiTraDAO = new HoaDonDoiTra_DAO(em);
        Faker faker = new Faker();
        Random random = new Random();

        // Lấy danh sách mã nhân viên và mã hóa đơn từ cơ sở dữ liệu
        List<String> maNhanVienList = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class).getResultList();
        List<String> maHoaDonList = em.createQuery("SELECT h.maHD FROM HoaDon h", String.class).getResultList();

        // 1. Tạo mới một hóa đơn đổi trả
        HoaDonDoiTra hoaDonDoiTra = new HoaDonDoiTra();
        hoaDonDoiTra.setMaHoaDonDoiTra(faker.bothify("HDDT-########"));
        hoaDonDoiTra.setNgayDoiTra(LocalDate.now());
        hoaDonDoiTra.setSoTienTra(faker.number().randomDouble(2, 100000, 500000));
        hoaDonDoiTra.setMoTa(faker.lorem().sentence(5));
        hoaDonDoiTra.setLoaiDoiTra(faker.bool().bool());
        hoaDonDoiTra.setMaNhanVien(em.find(NhanVien.class, maNhanVienList.get(random.nextInt(maNhanVienList.size()))));
        hoaDonDoiTra.setMaHoaDon(em.find(HoaDon.class, maHoaDonList.get(random.nextInt(maHoaDonList.size()))));

        // Gọi DAO để thêm mới
        boolean createSuccess = hoaDonDoiTraDAO.create(hoaDonDoiTra);
        if (createSuccess) {
            System.out.println("Tạo mới hóa đơn đổi trả thành công.");
        }

        // 2. Lấy tất cả hóa đơn đổi trả
        List<HoaDonDoiTra> danhSachHoaDonDoiTra = hoaDonDoiTraDAO.getAll();
        System.out.println("Danh sách hóa đơn đổi trả:");
        for (HoaDonDoiTra hddt : danhSachHoaDonDoiTra) {
            System.out.println(hddt);
        }

        // 3. Cập nhật thông tin hóa đơn đổi trả
        if (!danhSachHoaDonDoiTra.isEmpty()) {
            HoaDonDoiTra hoaDonCanSua = danhSachHoaDonDoiTra.get(0);
            hoaDonCanSua.setSoTienTra(hoaDonCanSua.getSoTienTra() + 50000);
            boolean updateSuccess = hoaDonDoiTraDAO.update(hoaDonCanSua);
            if (updateSuccess) {
                System.out.println("Cập nhật hóa đơn đổi trả thành công.");
            }
        }

      
        em.close();
        emf.close();
    }
}
