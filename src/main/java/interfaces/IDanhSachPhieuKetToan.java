package interfaces;

import entity.KetToan;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IDanhSachPhieuKetToan extends Remote {
    Optional<KetToan> findById(String id) throws Exception;
    List<KetToan> findAll() throws Exception;
    boolean create(KetToan ketToan) throws Exception;
    boolean update(KetToan ketToan) throws Exception;
    boolean delete(String id) throws Exception;
}
