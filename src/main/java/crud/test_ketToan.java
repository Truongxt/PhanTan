//package crud;
//
//import dao.DonViTinh_DAO;
//import dao.KetToan_DAO;
//import entity.BangKiemTien;
//import entity.DonViTinh;
//import entity.KetToan;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.TypedQuery;
//import net.datafaker.Faker;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//public class test_ketToan {
//    private static EntityManagerFactory emf;
//    private static EntityManager em;
//    private static KetToan_DAO ketToanDAO;
//
//    public static void main(String[] args) {
//        setUp();
//    }
//
//    public static void setUp() {
//        Random rd = new Random();
//        Date startDate = Date.from(LocalDate.of(2024, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date endDate = Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
//        emf = Persistence.createEntityManagerFactory("default");
//        em = emf.createEntityManager();
//        ketToanDAO = new KetToan_DAO(em);
//        Faker faker = new Faker();
//        TypedQuery<String> query = em.createQuery("SELECT n.maBangKiemTien FROM BangKiemTien n", String.class);
//        List<String> maBKT = query.getResultList();
//        KetToan kiemToan = new KetToan();
//        kiemToan.setMaKetToan(faker.bothify("KT-########"));
//        kiemToan.setNgayBatDau(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        kiemToan.setMaBangKiemTien(em.find(BangKiemTien.class, maBKT.get(rd.nextInt(maBKT.size()))));
//        kiemToan.setNgayKetThuc(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//
//        ketToanDAO.create(kiemToan);
//    }
//}
