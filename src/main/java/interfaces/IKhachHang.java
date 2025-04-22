package interfaces;

import entity.KhachHang;
import java.util.ArrayList;

public interface IKhachHang {
    boolean create(KhachHang kh);
    ArrayList<KhachHang> getAllKhachHang();
    ArrayList<KhachHang> timKiemTheoMa(String maKhachHang);
    KhachHang getKhachHangSDT(String soDienThoai);
    KhachHang getKhachHang(String maKH);
    String generateID();
    boolean taoMoi(KhachHang kh);
    boolean capNhat(String ma, KhachHang newKh);
    ArrayList<KhachHang> getAllTKKhachHang();
    ArrayList<KhachHang> getAllTKKhachHangMonth(int month, int year);
    ArrayList<KhachHang> getAllTKKhachHangYear(int year);
    ArrayList<KhachHang> getTKKhachHangDoanhThu(String dau);
}