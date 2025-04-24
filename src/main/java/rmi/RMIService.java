package rmi;

import dao.*;
import entity.KetToan;
import entity.NhaCungCap;
import entity.Voucher;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIService {
    public static void main(String[] args) throws Exception {
        // Tạo context JNDI
        Context context = new InitialContext();

        // Tạo RMI registry trên cổng 9090
        LocateRegistry.createRegistry(9090);

        BangKiemTien_DAO bangKiemTienDao = new BangKiemTien_DAO();
        ChiTietBangKiemTien_DAO chiTietBangKiemTienDao = new ChiTietBangKiemTien_DAO();
        ChiTietDoiTra_DAO chiTietDoiTraDao = new ChiTietDoiTra_DAO();
        ChiTietHoaDon_DAO chiTietHoaDonDao = new ChiTietHoaDon_DAO();
        DanhSachPhieuKetToan_DAO danhSachPhieuKetToanDao = new DanhSachPhieuKetToan_DAO();
        DanhSachPhieuKiemTien_DAO danhSachPhieuKiemTienDao = new DanhSachPhieuKiemTien_DAO();
        DoiTra_DAO doiTraDao = new DoiTra_DAO();
        DonViTinh_DAO donViTinhDao = new DonViTinh_DAO();
        HoaDon_DAO hoaDonDao = new HoaDon_DAO();
        KetToan_DAO ketToanDao = new KetToan_DAO();
        KhachHang_DAO khachHangDao = new KhachHang_DAO();
        KiemTien_DAO kiemTienDao = new KiemTien_DAO();
        LoaiThuoc_DAO loaiThuocDao = new LoaiThuoc_DAO();
        NhaCungCap_DAO nhaCungCapDao = new NhaCungCap_DAO();
        NhanVien_DAO nhanVienDao = new NhanVien_DAO();
        OTP_DAO otpDao = new OTP_DAO();
        TaiKhoan_DAO taiKhoanDao = new TaiKhoan_DAO();
        Thuoc_DAO thuocDao = new Thuoc_DAO();
        VaiTro_DAO vaiTroDao = new VaiTro_DAO();
        XuatXu_DAO xuatXuDao = new XuatXu_DAO();
        Voucher_DAO voucherDao = new Voucher_DAO();


        // Đăng ký các DAO vào RMI registry
        context.bind("rmi://localhost:9090/BangKiemTien_DAO", bangKiemTienDao);
        context.bind("rmi://localhost:9090/ChiTietBangKiemTien_DAO", chiTietBangKiemTienDao);
        context.bind("rmi://localhost:9090/ChiTietDoiTra_DAO", chiTietDoiTraDao);
        context.bind("rmi://localhost:9090/ChiTietHoaDon_DAO", chiTietHoaDonDao);
        context.bind("rmi://localhost:9090/DanhSachPhieuKetToan_DAO", danhSachPhieuKetToanDao);
        context.bind("rmi://localhost:9090/DanhSachPhieuKiemTien_DAO", danhSachPhieuKiemTienDao);
        context.bind("rmi://localhost:9090/DoiTra_DAO", doiTraDao);
        context.bind("rmi://localhost:9090/DonViTinh_DAO", donViTinhDao);
        context.bind("rmi://localhost:9090/HoaDon_DAO", hoaDonDao);
        context.bind("rmi://localhost:9090/KetToan_DAO", ketToanDao);
        context.bind("rmi://localhost:9090/KhachHang_DAO", khachHangDao);
        context.bind("rmi://localhost:9090/KiemTien_DAO", kiemTienDao);
        context.bind("rmi://localhost:9090/LoaiThuoc_DAO", loaiThuocDao);
        context.bind("rmi://localhost:9090/NhanVien_DAO", nhanVienDao);
        context.bind("rmi://localhost:9090/OTP_DAO", otpDao);
        context.bind("rmi://localhost:9090/TaiKhoan_DAO", taiKhoanDao);
        context.bind("rmi://localhost:9090/Thuoc_DAO", thuocDao);
        context.bind("rmi://localhost:9090/VaiTro_DAO", vaiTroDao);
        context.bind("rmi://localhost:9090/XuatXu_DAO", xuatXuDao);
        context.bind("rmi://localhost:9090/Voucher_DAO", voucherDao);
        context.bind("rmi://localhost:9090/NhaCungCap_DAO", nhaCungCapDao);



        System.out.println("RMI server is running...");
    }
}