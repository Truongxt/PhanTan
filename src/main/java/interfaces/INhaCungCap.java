package interfaces;

import entity.NhaCungCap;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface INhaCungCap extends Remote {
    NhaCungCap create(NhaCungCap ncc);
    NhaCungCap getById(String maNCC);
    List<NhaCungCap> getAll();
    boolean update(String maNCC, NhaCungCap newNCC);
    boolean delete(String maNCC);
    List<NhaCungCap> searchByName(String name);
    List<NhaCungCap> searchByPhoneNumber(String phoneNumber);
}
