import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import net.datafaker.Faker;
import org.hibernate.Transaction;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.hibernate.boot.jaxb.mapping.spi.JaxbPersistentAttribute;
import org.hibernate.boot.xsd.MappingXsdSupport;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();
        EntityTransaction tr = em.getTransaction();

        Faker faker = new Faker();
        Random rd= new Random();
        Date startDate = Date.from(LocalDate.of(2024, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

//XuatXu
//        for (int i = 0; i < 20; i++) {
//            String tenXuatXu = faker.address().country();
//            XuatXu xuatXu = new XuatXu();
//            xuatXu.setMaXuatXu(faker.bothify("XX-########"));
//            xuatXu.setTenXuatXu(tenXuatXu);
//
//            try {
//                tr.begin();
//                em.persist(xuatXu);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }

//Don vi tinh
//        String[] tenDVT={"Cái","Hộp","Chai","Lon","Gói","Túi","Bịch","Cây","Cục","Bộ"};
//        for (int i = 0; i < 20; i++) {
//
//            String tenDonViTinh = tenDVT[rd.nextInt(tenDVT.length)];
//            String maDonViTinh = faker.number().digits(5);
//            DonViTinh donViTinh = new DonViTinh();
//            donViTinh.setTen(tenDonViTinh);
//            donViTinh.setMaDonViTinh(maDonViTinh);
//
//            try {
//                tr.begin();
//                em.persist(donViTinh);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }
//
//        // NhaCungCap
//            for (int i = 0; i <20; i++) {
//                boolean trangThai=rd.nextBoolean();
//                NhaCungCap nhaCungCap = new NhaCungCap();
//                String  maNCC=faker.bothify("NCC-#########");
//                String tenNCC=faker.company().name();
//                String diaChi=faker.address().fullAddress();
//                String soDienThoai=faker.phoneNumber().cellPhone();
//                nhaCungCap.setMaNCC(maNCC);
//                nhaCungCap.setTenNCC(tenNCC);
//                nhaCungCap.setDiaChi(diaChi);
//                nhaCungCap.setSdt(soDienThoai);
//                nhaCungCap.setTrangThai(faker.bool().bool());
//             //   nhaCungCap.setTrangThai(trangThai);
//                nhaCungCap.setEmail(faker.internet().emailAddress());
//
//                try {
//                    tr.begin();
//                    em.persist(nhaCungCap);
//                    tr.commit();
//                } catch (Exception e) {
//                    tr.rollback();
//                    e.printStackTrace();
//                }
//
//        }

//KHach Hang



//
//        for (int i = 0; i < 500; i++) {
//            KhachHang kh = new KhachHang();
//            kh.setMaKhachHang(faker.bothify("KH-########"));
//            kh.setTenKhachHang(faker.name().fullName());
//            kh.setSdt(faker.phoneNumber().cellPhone());
//            kh.setDiaChi(faker.address().fullAddress());
//            kh.setNgayLapTaiKhoan(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//
//
//            try {
//                tr.begin();
//                em.persist(kh);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//
//        }


// vai tro

//        String[] tenVaiTro={"Admin","Nhân viên","Quản lí"};
//
//        for (int i = 0; i < tenVaiTro.length; i++) {
//            String tenVaiTro1 = tenVaiTro[i];
//            VaiTro vaiTro = new VaiTro();
//            vaiTro.setTenVaiTro(tenVaiTro1);
//            String maVaiTro= faker.bothify("VT-########");
//            vaiTro.setMaVaiTro(maVaiTro);
//            try {
//                tr.begin();
//                em.persist(vaiTro);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//
//        }

// Nhân viên
//        String[] tenNV={"Lê Minh Tiến", "Trần Việt Nhân", "Trần Xuân Trường","Nguyễn Thị Hà Như","Nguyễn Nhật Dương"};
//        for (int i = 0; i < tenNV.length; i++) {
//            String tenNhanVien = tenNV[i];
//            NhanVien nhanVien = new NhanVien();
//            String maNV= faker.bothify("NV-########");
//            nhanVien.setTenNhanVien(tenNhanVien);
//            nhanVien.setSdt(faker.phoneNumber().cellPhone());
//            nhanVien.setDiaChi(faker.address().fullAddress());
//            nhanVien.setEmail(faker.internet().emailAddress());
//            nhanVien.setCccd(faker.regexify("[0-9]{12}"));
//            nhanVien.setMaNhanVien(maNV);
//            nhanVien.setNgayVaoLam(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            nhanVien.setTrangThai(faker.bool().bool());
//            try {
//                tr.begin();
//                em.persist(nhanVien);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }


        // tài khoản

//        TypedQuery<String> query = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class);
//        List<String> maNhanVienList = query.getResultList();
//
//        TypedQuery<VaiTro> query1 = em.createQuery("SELECT v FROM VaiTro v", VaiTro.class);
//        List<VaiTro> vaiTroList = query1.getResultList();
//
//        for (String maNhanVien : maNhanVienList) {
//            NhanVien nv = em.find(NhanVien.class, maNhanVien);
//            TaiKhoan taiKhoan = new TaiKhoan();
//            taiKhoan.setMaNhanVien(nv);
//            taiKhoan.setTenTaiKhoan(faker.internet().emailAddress());
//            taiKhoan.setPassword(faker.internet().password());
//            VaiTro vt = vaiTroList.get(rd.nextInt(vaiTroList.size()));
//            taiKhoan.setMaVaiTro(vt);
//
//            try {
//                tr.begin();
//                em.persist(taiKhoan);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }


        //LoaiThuốc


     //   String[] loaiThuoc={"Vitamin", " Thực phẩm chức năng", "Thuốc giảm đau", "Thuốc giảm cân", "Thuốc chống viêm", "Thuốc chống sốt", "Thuốc chống dị ứng", "Thuốc chống co giật", "Thuốc chống trầm cảm", "Thuốc chống lo âu", "Thuốc chống say sóng", "Thuốc chống say tàu xe"};

//        for (int i=0;i<10;i++) {
//            String maThuoc = faker.bothify("TH-########");
//            LoaiThuoc thuoc = new LoaiThuoc();
//            thuoc.setMaLoai(maThuoc);
//            thuoc.setTenLoai(faker.medical().medicineName());
//            try {
//                tr.begin();
//                em.persist(thuoc);
//                tr.commit();
//
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }

//           //Thuoc
//            TypedQuery<String> query = em.createQuery("SELECT n.maLoai FROM LoaiThuoc n", String.class);
//           List<String> maLoaiThuoc = query.getResultList();
//
//        TypedQuery<String> query1 = em.createQuery("SELECT n.maDonViTinh FROM DonViTinh n", String.class);
//        List<String> maDVT = query1.getResultList();
//        System.out.println("MaDon vi tinh"+maDVT);
//        TypedQuery<String> query2 = em.createQuery("SELECT n.maXuatXu FROM XuatXu n", String.class);
//        List<String> maXuatXu = query2.getResultList();
//
//        TypedQuery<String> query3 = em.createQuery("SELECT n.maNCC FROM NhaCungCap n", String.class);
//        List<String> maNCC = query3.getResultList();
//
//        TypedQuery<String> query4 = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class);
//        List<String> maNV = query4.getResultList();
//
//           for(int i=0;i<100;i++){
//                Thuoc thuoc = new Thuoc();
//                thuoc.setMaThuoc(faker.bothify("TH-########"));
//                thuoc.setTenThuoc(faker.medical().medicineName());
//                thuoc.setGia(faker.number().randomDouble(2, 1000, 100000));
//                thuoc.setSoLuongTon(faker.number().numberBetween(1, 100));
//                thuoc.setHsd(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//                thuoc.setNsx(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//                thuoc.setMaLoai(em.find(LoaiThuoc.class, maLoaiThuoc.get(rd.nextInt(maLoaiThuoc.size()))));
//                thuoc.setMaDonViTinh(em.find(DonViTinh.class, maDVT.get(rd.nextInt(maDVT.size()))));
//                thuoc.setMaXuatXu(em.find(XuatXu.class, maXuatXu.get(rd.nextInt(maXuatXu.size()))));
//                thuoc.setMaNCC(em.find(NhaCungCap.class, maNCC.get(rd.nextInt(maNCC.size()))));
//                thuoc.setThue(rd.nextDouble());
//                thuoc.setMota(faker.lorem().sentence());
//                try {
//                     tr.begin();
//                     em.persist(thuoc);
//                     tr.commit();
//                } catch (Exception e) {
//                     tr.rollback();
//                     e.printStackTrace();
//           }
//
//    }


// ma bang kiem tien

//        for (int i = 0; i <100; i++) {
//            BangKiemTien bangKiemThuoc = new BangKiemTien();
//            bangKiemThuoc.setMaBangKiemTien(faker.bothify("BKT-########"));
//            bangKiemThuoc.setNgayBatDau(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            bangKiemThuoc.setNgayKetThuc((faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
//            try {
//                tr.begin();
//                em.persist(bangKiemThuoc);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }

// ket toan
//        TypedQuery<String> query = em.createQuery("SELECT n.maBangKiemTien FROM BangKiemTien n", String.class);
//      List<String> maBKT = query.getResultList();
//        for (int i = 0; i < 100; i++) {
//            KetToan kiemToan = new KetToan();
//            kiemToan.setMaKetToan(faker.bothify("KT-########"));
//            kiemToan.setNgayBatDau(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            kiemToan.setMaBangKiemTien(em.find(BangKiemTien.class, maBKT.get(rd.nextInt(maBKT.size()))));
//            kiemToan.setNgayKetThuc(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//           try{
//                tr.begin();
//                em.persist(kiemToan);
//                tr.commit();
//           }catch (Exception e){
//                tr.rollback();
//                e.printStackTrace();
//           }
//        }


//        //HoaDon

//
//        TypedQuery<String> query = em.createQuery("SELECT n.maKhachHang FROM KhachHang n", String.class);
//        List<String> maKH = query.getResultList();
//        TypedQuery<String> query1 = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class);
//        List<String> maNV = query1.getResultList();
//        TypedQuery<String> query2 = em.createQuery("SELECT n.maKetToan FROM KetToan n", String.class);
//        List<String> maKT = query2.getResultList();
//
//        for (int i = 0; i < 500; i++) {
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setMaHD(faker.bothify("HD-########"));
//            hoaDon.setNgayLap(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            hoaDon.setTongTien(faker.number().randomDouble(2, 100000, 1000000));
//            hoaDon.setMaKH(em.find(KhachHang.class, maKH.get(rd.nextInt(maKH.size()))));
//            hoaDon.setMaNhanVien(em.find(NhanVien.class, maNV.get(rd.nextInt(maNV.size()))));
//            hoaDon.setAtm(faker.bool().bool());
//            hoaDon.setTrangThai(faker.bool().bool());
//            hoaDon.setTienDaDua(faker.number().randomDouble(2, 10000, 1000000));
//            hoaDon.setMaKetToan(em.find(KetToan.class, maKT.get(rd.nextInt(maKT.size()))));
//            try {
//                tr.begin();
//                em.persist(hoaDon);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }

        //chi tiet hoa don

//        TypedQuery<String> query = em.createQuery("SELECT n.maHD FROM HoaDon n", String.class);
//        List<String> maHD = query.getResultList();
//        TypedQuery<String> query1 = em.createQuery("SELECT n.maThuoc FROM Thuoc n", String.class);
//        List<String> maThuoc = query1.getResultList();
//        System.out.println("Ma HD"+maHD);
//        for (int i = 0; i < 1000; i++) {
//            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
//
//            chiTietHoaDon.setDonGia(faker.number().randomDouble(2, 1000, 100000));
//            chiTietHoaDon.setSoLuong(faker.number().numberBetween(1, 100));
//            String maThuoc1=maThuoc.get(rd.nextInt(maThuoc.size()));
//            String maHD1=maHD.get(rd.nextInt(maHD.size()));
//            chiTietHoaDon.setMaHD(em.find(HoaDon.class, maHD.get(rd.nextInt(maHD.size()))));
//            chiTietHoaDon.setMaThuoc(em.find(Thuoc.class,maThuoc1 ));
//            ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId(maHD1, maThuoc1);
//
//            chiTietHoaDon.setId(chiTietHoaDonId);
//            try{
//                tr.begin();
//                em.persist(chiTietHoaDon);
//                tr.commit();
//            }catch (Exception e){
//                tr.rollback();
//                e.printStackTrace();
//            }
//
//
//        }


        // hoa don doi tra

//        TypedQuery<String> query = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class);
//        List<String> maNV = query.getResultList();
//        TypedQuery<String> query1 = em.createQuery("SELECT n.maHD FROM HoaDon n", String.class);
//        List<String> maHD = query1.getResultList();
//
//
//
//        for (int i = 0; i < 100; i++) {
//            HoaDonDoiTra hoaDonDoiTra = new HoaDonDoiTra();
//            hoaDonDoiTra.setLoaiDoiTra(faker.bool().bool());
//            hoaDonDoiTra.setMoTa(faker.lorem().sentence());
//            hoaDonDoiTra.setNgayDoiTra(faker.date().between(startDate, endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            hoaDonDoiTra.setSoTienTra(faker.number().randomDouble(2, 1000, 100000));
//            hoaDonDoiTra.setMaNhanVien(em.find(NhanVien.class, maNV.get(rd.nextInt(maNV.size()))));
//            hoaDonDoiTra.setMaHoaDon(em.find(HoaDon.class, maHD.get(rd.nextInt(maHD.size()))));
//            hoaDonDoiTra.setMaHoaDonDoiTra(faker.bothify("HDDT-########"));
//            try{
//                tr.begin();
//                em.persist(hoaDonDoiTra);
//                tr.commit();
//            }catch (Exception e){
//                tr.rollback();
//                e.printStackTrace();
//            }
//        }



        // chi tiet doi tra
//        TypedQuery<String> query = em.createQuery("SELECT n.maHoaDonDoiTra FROM HoaDonDoiTra n", String.class);
//        List<String> maHDDT = query.getResultList();
//        TypedQuery<String> query1 = em.createQuery("SELECT n.maThuoc FROM Thuoc n", String.class);
//        List<String> maThuoc = query1.getResultList();
//
//
//        for (int i = 0; i < 100; i++) {
//            ChiTietDoiTra chiTietDoiTra = new ChiTietDoiTra();
//            Thuoc thuoc1= em.find(Thuoc.class, maThuoc.get(rd.nextInt(maThuoc.size())));
//            HoaDonDoiTra hoaDonDoiTra1= em.find(HoaDonDoiTra.class, maHDDT.get(rd.nextInt(maHDDT.size())));
//            ChiTietDoiTraId chiTietDoiTraId = new ChiTietDoiTraId();
//            chiTietDoiTraId.setMaHoaDonDoiTra(hoaDonDoiTra1.getMaHoaDonDoiTra());
//            chiTietDoiTraId.setMaThuoc(thuoc1.getMaThuoc());
//            chiTietDoiTra.setId(chiTietDoiTraId);
//            chiTietDoiTra.setSoLuong(faker.number().numberBetween(1, 100));
//            chiTietDoiTra.setDonGia(faker.number().randomDouble(2, 1000, 100000));
//            chiTietDoiTra.setMaThuoc(thuoc1);
//            chiTietDoiTra.setMaHoaDonDoiTra(hoaDonDoiTra1);
//            try{
//                tr.begin();
//                em.persist(chiTietDoiTra);
//                tr.commit();
//
//        }catch (Exception e){
//            tr.rollback();
//            e.printStackTrace();
//        }
//        }

        // kiem tien

//        TypedQuery<String> query = em.createQuery("SELECT n.maBangKiemTien FROM BangKiemTien n", String.class);
//        List<String> maBKT = query.getResultList();
//
//        for (int i = 0; i < 100; i++) {
//            KiemTien kiemTien = new KiemTien();
//            KiemTienId kiemTienId = new KiemTienId();
//            BangKiemTien maBKT1 = em.find(BangKiemTien.class,maBKT.get(rd.nextInt(maBKT.size())));
//            Double giaTri = faker.number().randomDouble(2, 1000, 100000);
//
//            kiemTienId.setGiaTri(giaTri);
//            kiemTienId.setMaBangKiemTien(maBKT1.getMaBangKiemTien());
//
//            kiemTien.setId(kiemTienId);
//            kiemTien.setMaBangKiemTien(maBKT1);
//            kiemTien.setSoLuong(faker.number().numberBetween(1, 100));
//
//            try {
//                tr.begin();
//                em.persist(kiemTien);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//
//
//        }


        // chi tiet bang kiem tien
//        TypedQuery<String> query = em.createQuery("SELECT n.maNhanVien FROM NhanVien n", String.class);
//        List<String> maNV = query.getResultList();
//
//        TypedQuery<String> query1 = em.createQuery("SELECT n.maBangKiemTien FROM BangKiemTien n", String.class);
//        List<String> maBKT = query1.getResultList();
//        for (int i = 0; i < 100; i++) {
//
//            NhanVien nv= em.find(NhanVien.class, maNV.get(rd.nextInt(maNV.size())));
//            BangKiemTien bkt= em.find(BangKiemTien.class, maBKT.get(rd.nextInt(maBKT.size())));
//            boolean chiSoKiem = faker.bool().bool();
//
//            ChiTietBangKiemTien chiTietBangKiemTien = new ChiTietBangKiemTien();
//            ChiTietBangKiemTienId chiTietBangKiemTienId = new ChiTietBangKiemTienId();
//
//            chiTietBangKiemTienId.setMaBangKiemTien(bkt.getMaBangKiemTien());
//            chiTietBangKiemTienId.setChiSoKiem(chiSoKiem);
//
//            chiTietBangKiemTien.setId(chiTietBangKiemTienId);
//            chiTietBangKiemTien.setMaBangKiemTien(bkt);
//            chiTietBangKiemTien.setMaNhanVien(nv);
//
//
//            try {
//                tr.begin();
//                em.persist(chiTietBangKiemTien);
//                tr.commit();
//            } catch (Exception e) {
//                tr.rollback();
//                e.printStackTrace();
//            }
//
//
//        }
    }
}
