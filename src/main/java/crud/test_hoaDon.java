package crud;

import dao.HoaDon_DAO;
import entity.HoaDon;
import entity.KetToan;
import entity.KhachHang;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class test_hoaDon {
    public static void main(String[] args) {
        Date startDate = Date.from(LocalDate.of(2024, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Random rd= new Random();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Faker faker  = new Faker();
        HoaDon_DAO hoaDonDAO = new HoaDon_DAO(em);
        TypedQuery<String> query = em.createQuery("SELECT n.maKhachHang FROM KhachHang n", String.class);
        List<String> maKH = query.getResultList();
        TypedQuery<String> query1 = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class);
        List<String> maNV = query1.getResultList();
        TypedQuery<String> query2 = em.createQuery("SELECT n.maKetToan FROM KetToan n", String.class);
        List<String> maKT = query2.getResultList();
         HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(faker.bothify("HD-########"));
            hoaDon.setNgayLap(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            hoaDon.setTongTien(faker.number().randomDouble(2, 100000, 1000000));
            hoaDon.setMaKH(em.find(KhachHang.class, maKH.get(rd.nextInt(maKH.size()))));
            hoaDon.setMaNhanVien(em.find(NhanVien.class, maNV.get(rd.nextInt(maNV.size()))));
            hoaDon.setAtm(faker.bool().bool());
            hoaDon.setTrangThai(faker.bool().bool());
            hoaDon.setTienDaDua(faker.number().randomDouble(2, 10000, 1000000));
            hoaDon.setMaKetToan(null);
            //create
            hoaDonDAO.create(hoaDon);

            // find
            hoaDonDAO.getAll();
    }
}
