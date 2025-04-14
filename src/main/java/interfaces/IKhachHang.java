package interfaces;

import entity.KhachHang;

import java.util.List;
import java.util.Optional;

public interface IKhachHang {
    Optional<KhachHang> findById(String maKhachHang);

    List<KhachHang> findByTen(String tenKhachHang);

    List<KhachHang> findBySdt(String sdt);

    boolean create(KhachHang khachHang);

    boolean update(KhachHang khachHang);

    boolean delete(String maKhachHang);
}
