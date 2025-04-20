package interfaces;

import entity.DoiTra;
import java.util.ArrayList;

public interface IDoiTra {
    DoiTra getOne(String id) throws Exception;
    ArrayList<DoiTra> getAll() throws Exception;
    boolean create(DoiTra doiTra) throws Exception;
    boolean update(String id, DoiTra doiTra) throws Exception;
    boolean isReturnOrderExist(String maHoaDon) throws Exception;
    ArrayList<DoiTra> findById(String returnOrderID) throws Exception;
    ArrayList<DoiTra> filter(int type) throws Exception;
    int getNumberOfReturnOrderInMonth(int month, int year) throws Exception;
    double getTotalReturnOrderInMonth(int month, int year) throws Exception;
    DoiTra calculateTotals() throws Exception;
}