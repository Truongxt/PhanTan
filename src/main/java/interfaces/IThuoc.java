package interfaces;

import entity.Thuoc;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface IThuoc extends Remote {
    boolean create(Thuoc thuoc);
    Thuoc getThuocTheoMa(String maThuoc);
    Thuoc getThuocTheoTen(String tenThuoc);
    ArrayList<Thuoc> getAllThuoc();

    boolean suaThuoc(String maThuoc, Thuoc newThuoc);

    int getSize();

    ArrayList<Thuoc> timKiemTheoMa(String ma);

    boolean Xoa(String maThuoc);
    ArrayList<Thuoc> timKiemTheoTen(String ten);

    ArrayList<Thuoc> filter(String loaiThuoc, String xuatXu, String ten, String ma);

    ArrayList<Thuoc> getThuocHetHan();

    boolean capNhatSoLuong(Thuoc thuoc, int soLuong);

    ArrayList<Thuoc> getThuocTonKhoThap();

    ArrayList<Thuoc> getThuocHetHan1Thang();

    ArrayList<Thuoc> getThuocTheoLoai(String tenLoai);

    String generateID();
}
