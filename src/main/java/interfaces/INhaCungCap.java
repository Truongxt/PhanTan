package interfaces;

import entity.NhaCungCap;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface INhaCungCap extends Remote {
    NhaCungCap create(NhaCungCap ncc) throws RemoteException;
    NhaCungCap getById(String maNCC) throws RemoteException;
    List<NhaCungCap> getAll() throws RemoteException;
    boolean update(String maNCC, NhaCungCap newNCC) throws RemoteException;
    boolean delete(String maNCC) throws RemoteException;
    List<NhaCungCap> searchByName(String name) throws RemoteException;
    List<NhaCungCap> searchByPhoneNumber(String phoneNumber) throws RemoteException;
}