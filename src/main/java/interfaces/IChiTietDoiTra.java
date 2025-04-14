package interfaces;

import entity.ChiTietDoiTra;

import java.util.List;
import java.util.Optional;

public interface IChiTietDoiTra {
    List<ChiTietDoiTra> getAll();

    Optional<ChiTietDoiTra> getOne(String maHDDT, String maThuoc);

    boolean create(ChiTietDoiTra chiTietDoiTra);

    boolean update(ChiTietDoiTra chiTietDoiTra);

    boolean delete(String maHDDT, String maThuoc);
}
