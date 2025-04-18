package interfaces;

import entity.KiemTien;

import java.rmi.Remote;
import java.util.List;

public interface IKiemTien extends Remote {

    boolean create(KiemTien kiemTien) throws Exception;

    KiemTien getKiemTienTheoMa(String maKiemTien) throws Exception;

    List<KiemTien> getAllKiemTien() throws Exception;

    boolean update(KiemTien kiemTien) throws Exception;

    boolean delete(String maKiemTien) throws Exception;
}
