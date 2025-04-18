package interfaces;

import entity.NhaCungCap;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface INhaCungCap extends Remote {
    Optional<NhaCungCap> findById(String maNhaCungCap) throws Exception;

    List<NhaCungCap> findByTen(String tenNhaCungCap) throws Exception;

    boolean create(NhaCungCap nhaCungCap) throws Exception;

    boolean update(NhaCungCap nhaCungCap) throws Exception;

    boolean delete(String maNhaCungCap) throws Exception;
}
