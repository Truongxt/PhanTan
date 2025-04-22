package interfaces;

import entity.KhachHang;
import java.util.ArrayList;

public interface IKhachHang {
    boolean create(KhachHang kh) throws Exception;
    ArrayList<KhachHang> getAllKhachHang() throws Exception;
    ArrayList<KhachHang> timKiemTheoMa(String maKhachHang) throws Exception;
    KhachHang getKhachHangSDT(String soDienThoai) throws Exception;
    KhachHang getKhachHang(String maKH) throws Exception;
    String generateID() throws Exception;
    boolean taoMoi(KhachHang kh) throws Exception;
    boolean capNhat(String ma, KhachHang newKh) throws Exception;
    ArrayList<KhachHang> getAllTKKhachHang() throws Exception;
    ArrayList<KhachHang> getAllTKKhachHangMonth(int month, int year) throws Exception;
    ArrayList<KhachHang> getAllTKKhachHangYear(int year) throws Exception;
    ArrayList<KhachHang> getTKKhachHangDoanhThu(String dau) throws Exception;
}