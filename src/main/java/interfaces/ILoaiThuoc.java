package interfaces;

import entity.LoaiThuoc;

import java.util.List;
import java.util.Optional;

public interface ILoaiThuoc {

    Optional<LoaiThuoc> findById(String maLoaiThuoc) throws Exception;

    List<LoaiThuoc> findAll() throws Exception;

    boolean create(LoaiThuoc loaiThuoc) throws Exception;

    boolean update(LoaiThuoc loaiThuoc) throws Exception;

    boolean delete(String maLoaiThuoc) throws Exception;
}
