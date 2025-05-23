package interfaces;

import entity.ChiTietBangKiemTien;
import entity.ChiTietBangKiemTienId;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IChiTietBangKiemTien extends Remote {
    ChiTietBangKiemTien getOne(String maBangKiemTien) throws Exception;

    List<ChiTietBangKiemTien> findAll() throws Exception;
    ArrayList<ChiTietBangKiemTien> getAllCashCountSheetDetailInCashCountSheet(String maBangKiemTien) throws Exception;

    boolean create(ChiTietBangKiemTien chiTietBangKiemTien) throws Exception;

    boolean delete(ChiTietBangKiemTienId id) throws Exception;
}
