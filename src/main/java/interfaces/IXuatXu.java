package interfaces;

import entity.XuatXu;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IXuatXu extends Remote {
    boolean create(XuatXu xx) throws RemoteException;
    ArrayList<XuatXu> getAllXuatXu() throws RemoteException;
    XuatXu getXuatXuById(String maXuatXu) throws RemoteException;
    boolean updateXuatXu(String maXuatXu, XuatXu newXuatXu) throws RemoteException;
    boolean deleteXuatXu(String maXuatXu) throws RemoteException;
    int getSize() throws RemoteException;
}