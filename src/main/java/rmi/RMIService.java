package rmi;

import dao.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIService {
    public static void main(String[] args) throws Exception {
        // Tạo context JNDI
        Context context = new InitialContext();

        // Tạo RMI registry trên cổng 9090
        LocateRegistry.createRegistry(9090);

        // Khởi tạo các DAO
        NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
        OTP_DAO otpDAO = new OTP_DAO();
        TaiKhoan_DAO taiKhoanDAO = new TaiKhoan_DAO();
        Thuoc_DAO thuocDAO = new Thuoc_DAO();
        XuatXu_DAO xuatXuDAO = new XuatXu_DAO();
        BangKiemTien_DAO bangKiemTienDAO = new BangKiemTien_DAO();
        DanhSachPhieuKiemTien_DAO danhSachPhieuKiemTienDAO = new DanhSachPhieuKiemTien_DAO();
        DanhSachPhieuKetToan_DAO danhSachPhieuKetToanDAO = new DanhSachPhieuKetToan_DAO();
        ChiTietBangKiemTien_DAO chiTietBangKiemTienDAO = new ChiTietBangKiemTien_DAO();
        VaiTro_DAO vaiTroDAO = new VaiTro_DAO();

        // Đăng ký các DAO vào RMI registry
        context.bind("rmi://localhost:9090/nhanVienDAO", nhanVienDAO);
        context.bind("rmi://localhost:9090/otpDAO", otpDAO);
        context.bind("rmi://localhost:9090/taiKhoanDAO", taiKhoanDAO);
        context.bind("rmi://localhost:9090/thuocDAO", thuocDAO);
        context.bind("rmi://localhost:9090/xuatXuDAO", xuatXuDAO);
        context.bind("rmi://localhost:9090/bangKiemTienDAO", bangKiemTienDAO);
        context.bind("rmi://localhost:9090/danhSachPhieuKiemTienDAO", danhSachPhieuKiemTienDAO);
        context.bind("rmi://localhost:9090/danhSachPhieuKetToanDAO", danhSachPhieuKetToanDAO);
        context.bind("rmi://localhost:9090/chiTietBangKiemTienDAO", chiTietBangKiemTienDAO);
        context.bind("rmi://localhost:9090/vaiTroDAO", vaiTroDAO);

        System.out.println("RMI server is running...");
    }
}