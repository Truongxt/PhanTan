package interfaces;

import entity.NhanVien;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface INhanVien extends Remote {
    Optional<NhanVien> findById(String maNhanVien) throws Exception;

    List<NhanVien> findByMa(String maNhanVien) throws Exception;

    List<NhanVien> findByTen(String tenNhanVien) throws Exception;

    List<NhanVien> findByTrangThai(boolean trangThai) throws Exception;

    List<NhanVien> findBySdt(String sdt) throws Exception;

    boolean create(NhanVien nhanVien) throws Exception;

    boolean update(NhanVien nhanVien) throws Exception;

    boolean delete(String maNhanVien) throws Exception;
}
