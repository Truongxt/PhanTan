//// src/main/java/crud/test_Thuoc.java
//package crud;
//
//import dao.Thuoc_DAO;
//import entity.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
//import java.time.LocalDate;
//
//public class test_Thuoc {
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf = null;
//        EntityManager em = null;
//
//        try {
//            emf = Persistence.createEntityManagerFactory("default");
//            em = emf.createEntityManager();
//            EntityTransaction tr = em.getTransaction();
//
//            Thuoc_DAO thuocDAO = new Thuoc_DAO(em);
//
//            // Tạo một Thuoc mới
//            LocalDate date= LocalDate.of(2022, 12, 31);
//            DonViTinh donViTinh = new DonViTinh();
//            donViTinh=em.find(DonViTinh.class,"02030");
//            LoaiThuoc loaiThuoc = em.find(LoaiThuoc.class,"TH-02226450");
//            NhaCungCap ncc=em.find(NhaCungCap.class,"NCC-030957964");
//            XuatXu xx=em.find(XuatXu.class,"XX-00524330");
//            Thuoc newThuoc = new Thuoc();
//            newThuoc.setMaThuoc("T-001");
//            newThuoc.setTenThuoc("Paracetamol");
//            newThuoc.setGia(5000.0);
//            newThuoc.setHsd(date);
//            newThuoc.setMaDonViTinh(donViTinh);
//            newThuoc.setMaLoai(loaiThuoc);
//            newThuoc.setMaNCC(ncc);
//            newThuoc.setMaXuatXu(xx);
//            newThuoc.setMota("Thuoc chua dau");
//            newThuoc.setNsx(date);
//            newThuoc.setSoLuongTon(100);
//
//            newThuoc.setThue(0.1);
//            thuocDAO.create(newThuoc);
//
//            System.out.println("Thuoc moi da duoc tao: " + newThuoc);
//
//            // Cập nhật Thuoc
//            newThuoc.setTenThuoc("Paracetamol 500mg");
//            newThuoc.setGia(5500.0);
//
//
//       //     thuocDAO.update(newThuoc);
//
//            System.out.println("Thuoc da duoc cap nhat: " + newThuoc);
//
//            // Xóa Thuoc
//
//          //  thuocDAO.delete(newThuoc.getMaThuoc());
//
//            System.out.println("Thuoc da duoc xoa: " + newThuoc.getMaThuoc());
//
//            // tìm thuôc
//            // Find and print a Thuoc by its ID
//            String maThuoc = "T-001"; // Replace with the actual Thuoc ID
//            Thuoc thuoc = thuocDAO.getThuocTheoMa(maThuoc);
//            if (thuoc != null) {
//                System.out.println("Thuoc tim thay: " + thuoc);
//            } else {
//                System.out.println("Khong tim thay thuoc voi ma: " + maThuoc);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//            if (emf != null) {
//                emf.close();
//            }
//        }
//    }
//}