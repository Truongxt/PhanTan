package interfaces;

import entity.Otp;

import java.util.List;
import java.util.Optional;

public interface IOTP {
    Optional<Otp> findById(String maOtp);

    List<Otp> findAll();

    boolean create(Otp otp);

    boolean update(Otp otp);

    boolean delete(String maOtp);
}
