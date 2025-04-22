package interfaces;

import entity.TaiKhoan;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ITaiKhoan extends Remote {
    boolean create(TaiKhoan tk) throws RemoteException;
    ArrayList<TaiKhoan> getAllTaiKhoan() throws RemoteException;
    TaiKhoan getTaiKhoan(String ten) throws RemoteException;
    boolean updateTaiKhoan(TaiKhoan tk) throws RemoteException;
    boolean deleteTaiKhoan(String ten) throws RemoteException;
    boolean doiMatKHau(String ten, String mk) throws RemoteException;
}