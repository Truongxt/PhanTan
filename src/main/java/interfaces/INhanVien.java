package interfaces;

import entity.NhanVien;

import java.util.List;
import java.util.Optional;

public interface INhanVien {
    Optional<NhanVien> findById(String maNhanVien);

    List<NhanVien> findByMa(String maNhanVien);

    List<NhanVien> findByTen(String tenNhanVien);

    List<NhanVien> findByTrangThai(boolean trangThai);

    List<NhanVien> findBySdt(String sdt);

    boolean create(NhanVien nhanVien);

    boolean update(NhanVien nhanVien);

    boolean delete(String maNhanVien);
}
