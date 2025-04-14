package interfaces;

import entity.KiemTien;

import java.util.List;

public interface IKiemTien {

    boolean create(KiemTien kiemTien);

    KiemTien getKiemTienTheoMa(String maKiemTien);

    List<KiemTien> getAllKiemTien();

    boolean update(KiemTien kiemTien);

    boolean delete(String maKiemTien);
}
