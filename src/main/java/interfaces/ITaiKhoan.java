package interfaces;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface ITaiKhoan extends Remote {
    Optional<TaiKhoan> findById(String maTaiKhoan) throws Exception;
    List<TaiKhoan> findByTenTaiKhoan(String tenTaiKhoan) throws Exception;
    boolean create(TaiKhoan taiKhoan) throws Exception;
    boolean update(TaiKhoan taiKhoan) throws Exception;
    boolean delete(String maTaiKhoan) throws Exception;
}
