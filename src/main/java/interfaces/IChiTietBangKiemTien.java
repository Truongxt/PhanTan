package interfaces;

import entity.ChiTietBangKiemTien;
import entity.ChiTietBangKiemTienId;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IChiTietBangKiemTien extends Remote {
    Optional<ChiTietBangKiemTien> findById(ChiTietBangKiemTienId id) throws Exception;

    List<ChiTietBangKiemTien> findAll() throws Exception;

    boolean create(ChiTietBangKiemTien chiTietBangKiemTien) throws Exception;

    boolean update(ChiTietBangKiemTien chiTietBangKiemTien) throws Exception;

    boolean delete(ChiTietBangKiemTienId id) throws Exception;
}
