package interfaces;

import entity.KetToan;

import java.util.List;
import java.util.Optional;

public interface IDanhSachPhieuKetToan {
    Optional<KetToan> findById(String id);
    List<KetToan> findAll();
    boolean create(KetToan ketToan);
    boolean update(KetToan ketToan);
    boolean delete(String id);
}
