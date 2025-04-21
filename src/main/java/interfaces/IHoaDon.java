package interfaces;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.ThangVaDoanhThu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IHoaDon {

    Boolean create(HoaDon hoaDon);

    ArrayList<HoaDon> getAllHoaDon();

    HoaDon getHoaDon(String maHD);

    boolean suaHoaDon(String maHD, HoaDon newHoaDon);

    boolean deleteHoaDon(String maHD);

    int getSize();

    ArrayList<ChiTietHoaDon> getChiTietHoaDon(String maHD);

    ArrayList<ThangVaDoanhThu> getDoanhThuTheoThang(int nam);

    ArrayList<ThangVaDoanhThu> getDoanhThuTheoNgay(int thang, int nam);

    int getSizeOfMonth(int month, int year);

    int getSizeHoaDonTheoNgay(int day, int month, int year);

    ArrayList<HoaDon> getTatCaHoaDonTrongKetToan(String maKetToan);

    ArrayList<HoaDon> filter(String maHD, String sdt, double doanhThu, LocalDate ngayBatDau, LocalDate ngayKetThuc);

    ArrayList<HoaDon> getAllOrderInAcountingVoucher(String maKetToan);

    List<HoaDon> getHoaDonSuggestions(String keyword);

    String generateID(NhanVien nv);

    HoaDon createNewOrder(NhanVien nv) throws Exception;

    ArrayList<HoaDon> getAllHoaDonTam();

    boolean updateOrderAcountingVoucher(String orderID, String acountingVoucherID);

    int getSoLuongKhachHangThang(int month, int year);

    double getDoanhThuCuaNam(int year);

    int getSoLuongKhachHangNgay(int day, int month, int year);

    int getSizeOfYear(int year);

    int getSoLuongKhachHangNam(int year);

    int getSoHoaDonTheoKhachHang(String maKH);

    double getDoanhThuTheoKhachHang(String maKH);

    ArrayList<HoaDon> getAllHoaDonTrongKetToan(Date ngayBatDau, Date ngayKetThuc);
}