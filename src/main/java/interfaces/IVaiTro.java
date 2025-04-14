package interfaces;

import entity.VaiTro;

import java.util.List;
import java.util.Optional;

public interface IVaiTro {
    Optional<VaiTro> findById(String maVaiTro);
    List<VaiTro> findAll();
    boolean create(VaiTro vaiTro);
    boolean update(VaiTro vaiTro);
    boolean delete(String maVaiTro);
}
