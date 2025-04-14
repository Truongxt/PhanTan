package interfaces;

import entity.Thuoc;

import java.util.List;

public interface IThuoc {
    boolean create(Thuoc thuoc);
    Thuoc getThuocTheoMa(String mt);
    List<Thuoc> getAllThuoc();
    boolean update(Thuoc thuoc);
    boolean delete(String maThuoc);
}
