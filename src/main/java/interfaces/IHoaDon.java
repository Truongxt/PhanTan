package interfaces;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.ThangVaDoanhThu;

import java.rmi.Remote;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IHoaDon extends Remote {

    Boolean create(HoaDon hoaDon) throws Exception;

    ArrayList<HoaDon> getAllHoaDon() throws Exception;

    HoaDon getHoaDon(String maHD) throws Exception;

    boolean suaHoaDon(String maHD, HoaDon newHoaDon) throws Exception;

    boolean deleteHoaDon(String maHD) throws Exception;

    int getSize() throws Exception;

    ArrayList<ChiTietHoaDon> getChiTietHoaDon(String maHD) throws Exception;

    ArrayList<ThangVaDoanhThu> getDoanhThuTheoThang(int nam) throws Exception;

    ArrayList<ThangVaDoanhThu> getDoanhThuTheoNgay(int thang, int nam) throws Exception;

    int getSizeOfMonth(int month, int year) throws Exception;

    int getSizeHoaDonTheoNgay(int day, int month, int year) throws Exception;

    ArrayList<HoaDon> getTatCaHoaDonTrongKetToan(String maKetToan) throws Exception;

    ArrayList<HoaDon> filter(String maHD, String sdt, double doanhThu, LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    ArrayList<HoaDon> getAllOrderInAcountingVoucher(String maKetToan) throws Exception;

    List<HoaDon> getHoaDonSuggestions(String keyword) throws Exception;

    String generateID(NhanVien nv) throws Exception;

    HoaDon createNewOrder(NhanVien nv) throws Exception;

    ArrayList<HoaDon> getAllHoaDonTam() throws Exception;

    boolean updateOrderAcountingVoucher(String orderID, String acountingVoucherID) throws Exception;

    int getSoLuongKhachHangThang(int month, int year) throws Exception;

    double getDoanhThuCuaNam(int year) throws Exception;

    int getSoLuongKhachHangNgay(int day, int month, int year) throws Exception;

    int getSizeOfYear(int year) throws Exception;

    int getSoLuongKhachHangNam(int year) throws Exception;

    int getSoHoaDonTheoKhachHang(String maKH) throws Exception;

    double getDoanhThuTheoKhachHang(String maKH) throws Exception;

    ArrayList<HoaDon> getAllHoaDonTrongKetToan(Date ngayBatDau, Date ngayKetThuc) throws Exception;
}