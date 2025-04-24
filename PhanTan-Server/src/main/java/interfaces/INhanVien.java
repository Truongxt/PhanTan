package interfaces;

import entity.NhanVien;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface INhanVien extends Remote {
    Boolean create(NhanVien nv) throws RemoteException;
    ArrayList<NhanVien> getAllNhanVien() throws RemoteException;
    NhanVien getNhanVien(String maNhanVien) throws RemoteException;
    boolean suaNhanVien(String maNhanVien, NhanVien newNV) throws RemoteException;
    int getSize() throws RemoteException;
    NhanVien getLastNhanVien() throws RemoteException;
    ArrayList<NhanVien> timKiemTheoMa(String maNhanVien) throws RemoteException;
    NhanVien timKiemTheoMa1(String maNhanVien) throws RemoteException;
    ArrayList<NhanVien> timKiemTheoTen(String ten) throws RemoteException;
    ArrayList<NhanVien> timKiemTheoTrangThai(boolean tt) throws RemoteException;
    ArrayList<NhanVien> timTheoSDT(String soDT) throws RemoteException;
    String generateID() throws RemoteException;
}