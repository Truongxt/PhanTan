package interfaces;

import entity.DonViTinh;

import java.util.List;
import java.util.Optional;

public interface IDonViTinh {
    Optional<DonViTinh> findById(String maDonViTinh);
    List<DonViTinh> findAll();
    boolean create(DonViTinh donViTinh);
    boolean update(DonViTinh donViTinh);
    boolean delete(String maDonViTinh);
}
