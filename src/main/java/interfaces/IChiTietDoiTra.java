package interfaces;

import entity.ChiTietDoiTra;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IChiTietDoiTra extends Remote {
    List<ChiTietDoiTra> getAll();

    Optional<ChiTietDoiTra> getOne(String maHDDT, String maThuoc) throws Exception;

    boolean create(ChiTietDoiTra chiTietDoiTra) throws Exception;

    boolean update(ChiTietDoiTra chiTietDoiTra) throws Exception;

    boolean delete(String maHDDT, String maThuoc) throws Exception;
}
