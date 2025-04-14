package interfaces;

import entity.ChiTietBangKiemTien;
import entity.ChiTietBangKiemTienId;

import java.util.List;
import java.util.Optional;

public interface IChiTietBangKiemTien {
    Optional<ChiTietBangKiemTien> findById(ChiTietBangKiemTienId id);

    List<ChiTietBangKiemTien> findAll();

    boolean create(ChiTietBangKiemTien chiTietBangKiemTien);

    boolean update(ChiTietBangKiemTien chiTietBangKiemTien);

    boolean delete(ChiTietBangKiemTienId id);
}
