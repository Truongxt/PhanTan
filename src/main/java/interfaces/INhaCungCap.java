package interfaces;

import entity.NhaCungCap;

import java.util.List;
import java.util.Optional;

public interface INhaCungCap {
    Optional<NhaCungCap> findById(String maNhaCungCap);

    List<NhaCungCap> findByTen(String tenNhaCungCap);

    boolean create(NhaCungCap nhaCungCap);

    boolean update(NhaCungCap nhaCungCap);

    boolean delete(String maNhaCungCap);
}
