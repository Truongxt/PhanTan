package interfaces;

import entity.KetToan;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IDanhSachPhieuKetToan extends Remote {
    KetToan getOne(String maKetToan);

    ArrayList<KetToan> getAll();

    ArrayList<KetToan> getByDate(Date start, Date end);
}
