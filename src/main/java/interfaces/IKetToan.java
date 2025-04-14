package interfaces;

import entity.KetToan;

import java.util.List;
import java.util.Optional;

public interface IKetToan {
    Optional<KetToan> findById(String maKetToan);
    List<KetToan> findAll();
    boolean create(KetToan ketToan);
    boolean update(KetToan ketToan);
    boolean delete(String maKetToan);
}
