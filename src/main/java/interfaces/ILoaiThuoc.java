package interfaces;

import entity.LoaiThuoc;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ILoaiThuoc extends Remote {
    Boolean create(LoaiThuoc loaiThuoc) throws RemoteException;
    ArrayList<LoaiThuoc> getAllLoaiThuoc() throws RemoteException;
    LoaiThuoc getLoaiThuoc(String maLoai) throws RemoteException;
    boolean updateLoaiThuoc(String maLoai, LoaiThuoc newLoaiThuoc) throws RemoteException;
    int getSize() throws RemoteException;
    ArrayList<LoaiThuoc> searchByMaLoai(String maLoai) throws RemoteException;
    ArrayList<LoaiThuoc> searchByTenLoai(String tenLoai) throws RemoteException;
}