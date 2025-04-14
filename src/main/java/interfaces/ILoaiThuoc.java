package interfaces;

import entity.LoaiThuoc;

import java.util.List;
import java.util.Optional;

public interface ILoaiThuoc {

    Optional<LoaiThuoc> findById(String maLoaiThuoc);

    List<LoaiThuoc> findAll();

    boolean create(LoaiThuoc loaiThuoc);

    boolean update(LoaiThuoc loaiThuoc);

    boolean delete(String maLoaiThuoc);
}
