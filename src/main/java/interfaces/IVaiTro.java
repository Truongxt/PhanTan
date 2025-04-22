package interfaces;

import entity.VaiTro;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IVaiTro extends Remote {
    ArrayList<VaiTro> getAllVaiTro() throws RemoteException;
    VaiTro getVaiTro(String maVaiTro) throws RemoteException;
}