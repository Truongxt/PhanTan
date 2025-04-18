package interfaces;

import entity.Otp;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IOTP extends Remote {
    Optional<Otp> findById(String maOtp) throws Exception;

    List<Otp> findAll() throws Exception;

    boolean create(Otp otp) throws Exception;

    boolean update(Otp otp) throws Exception;

    boolean delete(String maOtp) throws Exception;
}
