package interfaces;

import entity.KetToan;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IDanhSachPhieuKetToan extends Remote {
    KetToan getOne(String maKetToan) throws Exception;
     ArrayList<KetToan> getAll()throws Exception;
    ArrayList<KetToan> getByDate(Date start, Date end) throws Exception;
}
