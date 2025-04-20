package interfaces;

import entity.NhanVien;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface INhanVien extends Remote {
    Boolean create(NhanVien nv);

    ArrayList<NhanVien> getAllNhanVien();

    NhanVien getNhanVien(String maNhanVien);

    boolean suaNhanVien(String maNhanVien, NhanVien newNV);

    int getSize();

    NhanVien getLastNhanVien();

    ArrayList<NhanVien> timKiemTheoMa(String maNhanVien);

    NhanVien timKiemTheoMa1(String maNhanVien);

    ArrayList<NhanVien> timKiemTheoTen(String ten);

    ArrayList<NhanVien> timKiemTheoTrangThai(boolean tt);

    ArrayList<NhanVien> timTheoSDT(String soDT);

    String generateID();
}
