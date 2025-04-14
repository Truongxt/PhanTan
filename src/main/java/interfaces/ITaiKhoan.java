package interfaces;

import entity.TaiKhoan;

import java.util.List;
import java.util.Optional;

public interface ITaiKhoan {
    Optional<TaiKhoan> findById(String maTaiKhoan);
    List<TaiKhoan> findByTenTaiKhoan(String tenTaiKhoan);
    boolean create(TaiKhoan taiKhoan);
    boolean update(TaiKhoan taiKhoan);
    boolean delete(String maTaiKhoan);
}
