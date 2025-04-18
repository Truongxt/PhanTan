package interfaces;

import entity.KhachHang;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IKhachHang extends Remote {
    Optional<KhachHang> findById(String maKhachHang) throws Exception;

    List<KhachHang> findByTen(String tenKhachHang) throws Exception;

    List<KhachHang> findBySdt(String sdt) throws Exception;

    boolean create(KhachHang khachHang) throws Exception;

    boolean update(KhachHang khachHang) throws Exception;

    boolean delete(String maKhachHang) throws Exception;
}
