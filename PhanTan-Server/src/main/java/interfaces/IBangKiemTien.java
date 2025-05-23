package interfaces;

import entity.BangKiemTien;
import entity.KiemTien;
import entity.NhanVien;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBangKiemTien extends Remote {

    BangKiemTien getOne(String id) throws Exception;

    List<BangKiemTien> getAll() throws Exception;

    boolean create(BangKiemTien bangKiemTien) throws Exception;

    boolean update(BangKiemTien bangKiemTien) throws Exception;

    boolean delete(String id) throws Exception;
    String getMaMoiNhat(String ma) throws Exception;
    String taoMa(Date date) throws Exception;
    void createBangKiemTien(ArrayList<KiemTien> listKiemTien, ArrayList<NhanVien> listNhanVien, Date ngayBatDau) throws Exception;
    ArrayList<BangKiemTien> filter() throws Exception;

}
