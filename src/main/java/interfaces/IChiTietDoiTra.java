package interfaces;

import entity.ChiTietDoiTra;
import entity.DoiTra;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IChiTietDoiTra extends Remote {
    List<ChiTietDoiTra> getAll();

    ChiTietDoiTra getOne(String maHDDT, String maThuoc) throws Exception;

    boolean create(ChiTietDoiTra chiTietDoiTra) throws Exception;

    Boolean updateProduct(String id, int soLuong) throws Exception;
    Boolean updateRefund(ChiTietDoiTra chiTietDoiTra) throws Exception;
    List<ChiTietDoiTra> getAllForOrderReturnID(String returnOrderID) throws Exception;
    void createReturnOrderDetail(DoiTra newReturnOrder, ArrayList<ChiTietDoiTra> cart) throws Exception;
    List<ChiTietDoiTra> getReturnedAndExchangedDrugs() throws Exception;
}
