package interfaces;

import entity.BangKiemTien;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IDanhSachPhieuKiemTien extends Remote {
    ArrayList<BangKiemTien> getAll() throws Exception;
    BangKiemTien getOne(String id) throws Exception;
}
