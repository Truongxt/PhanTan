package interfaces;

import entity.Voucher;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVoucher extends Remote {
    Boolean create(Voucher voucher) throws RemoteException;

    List<Voucher> getAllVoucher() throws RemoteException;

    Voucher getVoucher(String maVoucher) throws RemoteException;

    boolean suaVoucher(String maVoucher, Voucher newVoucher) throws RemoteException;

    boolean xoaVoucher(String maVoucher) throws RemoteException;
}
