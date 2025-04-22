package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOTP extends Remote {
    String getOtpFromDatabase(String email) throws RemoteException;
    boolean saveOtpToDatabase(String tenTaiKhoan, String otp) throws RemoteException;
    boolean deleteOtpFromDatabase(String email) throws RemoteException;
    String getMaXacNhan(String ten) throws RemoteException;
    void deleteOTP60s() throws RemoteException;
    void deleteExpiredOTPs() throws RemoteException;
}
