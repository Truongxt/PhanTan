package interfaces;

import entity.Thuoc;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IThuoc extends Remote {
    boolean create(Thuoc thuoc) throws RemoteException;
    Thuoc getThuocTheoMa(String maThuoc) throws RemoteException;
    Thuoc getThuocTheoTen(String tenThuoc) throws RemoteException;
    ArrayList<Thuoc> getAllThuoc() throws RemoteException;
    boolean suaThuoc(String maThuoc, Thuoc newThuoc) throws RemoteException;
    int getSize() throws RemoteException;
    ArrayList<Thuoc> timKiemTheoMa(String ma) throws RemoteException;
    boolean Xoa(String maThuoc) throws RemoteException;
    ArrayList<Thuoc> timKiemTheoTen(String ten) throws RemoteException;
    ArrayList<Thuoc> filter(String loaiThuoc, String xuatXu, String ten, String ma) throws RemoteException;
    ArrayList<Thuoc> getThuocHetHan() throws RemoteException;
    boolean capNhatSoLuong(Thuoc thuoc, int soLuong) throws RemoteException;
    ArrayList<Thuoc> getThuocTonKhoThap() throws RemoteException;
    ArrayList<Thuoc> getThuocHetHan1Thang() throws RemoteException;
    ArrayList<Thuoc> getThuocTheoLoai(String tenLoai) throws RemoteException;
    String generateID() throws RemoteException;
    ArrayList<Thuoc> searchThuoc(String query) throws RemoteException;
}