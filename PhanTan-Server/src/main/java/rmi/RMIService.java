package rmi;

import dao.*;
import entity.KetToan;
import entity.NhaCungCap;
import entity.Voucher;
import interfaces.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIService {
    public static void main(String[] args) throws Exception {
        // Tạo context JNDI
        Context context = new InitialContext();

        // Tạo RMI registry trên cổng 9090
        LocateRegistry.createRegistry(9090);

        IBangKiemTien bangKiemTienDao = new BangKiemTien_DAO();
        IChiTietBangKiemTien chiTietBangKiemTienDao = new ChiTietBangKiemTien_DAO();
        IChiTietDoiTra chiTietDoiTraDao = new ChiTietDoiTra_DAO();
        IChiTietHoaDon chiTietHoaDonDao = new ChiTietHoaDon_DAO();
        IDanhSachPhieuKetToan danhSachPhieuKetToanDao = new DanhSachPhieuKetToan_DAO();
        IDanhSachPhieuKiemTien danhSachPhieuKiemTienDao = new DanhSachPhieuKiemTien_DAO();
        IDoiTra doiTraDao = new DoiTra_DAO();
        IDonViTinh donViTinhDao = new DonViTinh_DAO();
        IHoaDon hoaDonDao = new HoaDon_DAO();
        IKetToan ketToanDao = new KetToan_DAO();
        IKhachHang khachHangDao = new KhachHang_DAO();
        IKiemTien kiemTienDao = new KiemTien_DAO();
        ILoaiThuoc loaiThuocDao = new LoaiThuoc_DAO();
        INhaCungCap nhaCungCapDao = new NhaCungCap_DAO();
        INhanVien nhanVienDao = new NhanVien_DAO();
        IOTP otpDao = new OTP_DAO();
        ITaiKhoan taiKhoanDao = new TaiKhoan_DAO();
        IThuoc thuocDao = new Thuoc_DAO();
        IVaiTro vaiTroDao = new VaiTro_DAO();
        IXuatXu xuatXuDao = new XuatXu_DAO();
        IVoucher voucherDao = new Voucher_DAO();


        // Đăng ký các DAO vào RMI registry
        context.bind("rmi://localhost:9090/bangKiemTien", bangKiemTienDao);
        context.bind("rmi://localhost:9090/chiTietBangKiemTien", chiTietBangKiemTienDao);
        context.bind("rmi://localhost:9090/chiTietDoiTra", chiTietDoiTraDao);
        context.bind("rmi://localhost:9090/chiTietHoaDon", chiTietHoaDonDao);
        context.bind("rmi://localhost:9090/danhSachPhieuKetToan", danhSachPhieuKetToanDao);
        context.bind("rmi://localhost:9090/danhSachPhieuKiemTien", danhSachPhieuKiemTienDao);
        context.bind("rmi://localhost:9090/doiTra", doiTraDao);
        context.bind("rmi://localhost:9090/donViTinh", donViTinhDao);
        context.bind("rmi://localhost:9090/hoaDon", hoaDonDao);
        context.bind("rmi://localhost:9090/ketToan", ketToanDao);
        context.bind("rmi://localhost:9090/khachHang", khachHangDao);
        context.bind("rmi://localhost:9090/kiemTien", kiemTienDao);
        context.bind("rmi://localhost:9090/loaiThuoc", loaiThuocDao);
        context.bind("rmi://localhost:9090/nhanVien", nhanVienDao);
        context.bind("rmi://localhost:9090/otp", otpDao);
        context.bind("rmi://localhost:9090/taiKhoan", taiKhoanDao);
        context.bind("rmi://localhost:9090/thuoc", thuocDao);
        context.bind("rmi://localhost:9090/vaiTro", vaiTroDao);
        context.bind("rmi://localhost:9090/xuatXu", xuatXuDao);
        context.bind("rmi://localhost:9090/voucher", voucherDao);
        context.bind("rmi://localhost:9090/nhaCungCap", nhaCungCapDao);



        System.out.println("RMI server is running...");
    }
}