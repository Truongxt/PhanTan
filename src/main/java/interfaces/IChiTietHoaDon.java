package interfaces;

import entity.ChiTietHoaDon;

import java.util.List;

public interface IChiTietHoaDon {
    boolean create(ChiTietHoaDon chiTiet);

    boolean update(ChiTietHoaDon chiTiet);

    List<ChiTietHoaDon> getAllChiTietHoaDon();

    List<ChiTietHoaDon> getChiTietHoaDon(String maHoaDon);

    int getSize();
}
