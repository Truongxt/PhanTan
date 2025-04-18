package interfaces;

import entity.BangKiemTien;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface IBangKiemTien extends Remote {

    Optional<BangKiemTien> findById(String id) throws Exception;

    List<BangKiemTien> findAll() throws Exception;

    boolean create(BangKiemTien bangKiemTien) throws Exception;

    boolean update(BangKiemTien bangKiemTien) throws Exception;

    boolean delete(String id) throws Exception;

    

}
