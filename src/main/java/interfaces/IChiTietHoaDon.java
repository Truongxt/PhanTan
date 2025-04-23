package interfaces;

import entity.ChiTietHoaDon;
import entity.ThuocVaLuotBan;
import entity.ThuocvaDoanhThu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IChiTietHoaDon extends Remote {

    boolean create(ChiTietHoaDon chiTiet) throws RemoteException;

    List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException;

    ChiTietHoaDon getChiTietHoaDon(String maThuoc, String maHoaDon) throws RemoteException;

    boolean suaChiTietHoaDon(String maThuoc, String maHoaDon, ChiTietHoaDon newChiTiet) throws RemoteException;

    boolean deleteChiTietHoaDon(String maThuoc, String maHoaDon) throws RemoteException;

    int getSize() throws RemoteException;

    List<ThuocvaDoanhThu> getTop10ThuocCoDoanhThuCaoNhat() throws RemoteException;

    double getDoanhThu(String maThuoc) throws RemoteException;

    int getsoLuongBan(String maThuoc) throws RemoteException;

    List<ThuocVaLuotBan> getThuocCoLuotBanCaoNhatTrongThang(int thang, int nam) throws RemoteException;

    ThuocVaLuotBan getTop1ThuocCoLuotBanCaoNhatTrongThang(int thang, int nam) throws RemoteException;

    ThuocvaDoanhThu getTop1ThuocCoDoanhThuCaoNhatTrongThang(int thang, int nam) throws RemoteException;
    ArrayList<ThuocVaLuotBan> getThuocTheoThang(int month, int year) throws RemoteException;
}
