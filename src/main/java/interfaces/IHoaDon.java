package interfaces;

import entity.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IHoaDon extends Remote {

    Optional<HoaDon> findById(String maHoaDon) throws Exception;

    List<HoaDon> getAll() throws Exception;

    ArrayList<HoaDon> getAllHoaDon() throws RemoteException;
    HoaDon getHoaDon(String maHD) throws RemoteException;
    boolean suaHoaDon(String maHD, HoaDon newHoaDon) throws RemoteException;
    boolean deleteHoaDon(String maHD) throws RemoteException;
    int getSize() throws RemoteException;
    ArrayList<HoaDon> getChiTietHoaDon(String maHD) throws RemoteException;
    ArrayList<HoaDon> filter(String maHD, String sdt, double doanhThu, LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException;
}
