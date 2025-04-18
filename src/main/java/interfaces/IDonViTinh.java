package interfaces;

import entity.DonViTinh;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IDonViTinh extends Remote {
    Optional<DonViTinh> findById(String maDonViTinh) throws Exception;
    List<DonViTinh> findAll() throws Exception;
    boolean create(DonViTinh donViTinh) throws Exception;
    boolean update(DonViTinh donViTinh) throws Exception;
    boolean delete(String maDonViTinh)  throws Exception;
}
