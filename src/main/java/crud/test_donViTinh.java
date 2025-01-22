package crud;

import dao.DonViTinh_DAO;
import entity.DonViTinh;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import net.datafaker.Faker;

import java.util.List;

public class test_donViTinh {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static DonViTinh_DAO donViTinhDAO;

    public static void main(String[] args) {
        setUp();
    }

    public static void setUp() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        donViTinhDAO = new DonViTinh_DAO(em);
        Faker faker = new Faker();
        DonViTinh dvt = new DonViTinh();
        dvt.setMaDonViTinh(faker.bothify("DVT###"));
        dvt.setTen(faker.name().fullName());
        System.out.println(dvt.getMaDonViTinh());
        System.out.println(dvt.getTen());
        //Thêm đơn vị tính
//        donViTinhDAO.create(dvt);

        //tìm all đon vị tính

        List<DonViTinh> listDVT = donViTinhDAO.findAll();

        System.out.println(listDVT.size());
        //delete

//        donViTinhDAO.delete("DVT473");

        // sửa
        DonViTinh donViTinHNew= new DonViTinh();
        donViTinHNew.setMaDonViTinh("DVT014");
        donViTinHNew.setTen("Trường");
        donViTinhDAO.update("DVT014",donViTinHNew);

    }

}
