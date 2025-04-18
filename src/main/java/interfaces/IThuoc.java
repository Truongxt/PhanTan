package interfaces;

import entity.Thuoc;

import java.rmi.Remote;
import java.util.List;

public interface IThuoc extends Remote {
    boolean create(Thuoc thuoc) throws Exception;
    Thuoc getThuocTheoMa(String mt) throws Exception;
    List<Thuoc> getAllThuoc() throws Exception;
    boolean update(Thuoc thuoc) throws Exception;
    boolean delete(String maThuoc) throws Exception;
}
