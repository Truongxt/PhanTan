package interfaces;

import entity.VaiTro;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IVaiTro extends Remote {
    Optional<VaiTro> findById(String maVaiTro) throws Exception;
    List<VaiTro> findAll() throws Exception;
    boolean create(VaiTro vaiTro) throws Exception;
    boolean update(VaiTro vaiTro) throws Exception;
    boolean delete(String maVaiTro) throws Exception;
}
