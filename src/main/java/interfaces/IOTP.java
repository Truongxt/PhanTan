package interfaces;

import entity.Otp;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IOTP extends Remote {
    String getOtpFromDatabase(String email);
    boolean saveOtpToDatabase(String tenTaiKhoan, String otp);
    boolean deleteOtpFromDatabase(String email);
    String getMaXacNhan(String ten);
    void deleteOTP60s();
    void deleteExpiredOTPs();
}
