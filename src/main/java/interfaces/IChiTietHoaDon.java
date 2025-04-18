package interfaces;

import entity.ChiTietHoaDon;

import java.rmi.Remote;
import java.util.List;

public interface IChiTietHoaDon extends Remote {
    boolean create(ChiTietHoaDon chiTiet) throws Exception;

    boolean update(ChiTietHoaDon chiTiet) throws Exception;

    List<ChiTietHoaDon> getAllChiTietHoaDon() throws Exception;

    List<ChiTietHoaDon> getChiTietHoaDon(String maHoaDon) throws Exception;

    int getSize();
}
