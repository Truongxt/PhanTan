package interfaces;

import entity.BangKiemTien;
import entity.HoaDon;
import entity.KetToan;
import entity.KiemTien;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IKetToan extends Remote {
    KetToan findById(String maKetToan)  throws Exception;

    List<KetToan> findAll() throws Exception;

    boolean create(KetToan ketToan) throws Exception;

    boolean update(KetToan ketToan) throws Exception;

    boolean delete(String maKetToan) throws Exception;

    List<KetToan> filterByDateRange(java.util.Date startDate, java.util.Date endDate) throws Exception;

    double calculateTotalRevenue(String maKetToan) throws Exception;
     KetToan getKetToanCuoi() throws Exception;
    public List<KetToan> getDSKetToanTheoNgay(LocalDate ngay) throws Exception;
    ArrayList<HoaDon> getAllHoaDon(Date start, Date end) throws Exception;
    void taoPhieuKetToan(BangKiemTien bangKiemTien, Date ngayKetThuc) throws RemoteException;
}
