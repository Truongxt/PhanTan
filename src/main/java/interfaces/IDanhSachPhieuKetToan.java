package interfaces;

import entity.KetToan;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IDanhSachPhieuKetToan extends Remote {
<<<<<<< HEAD
    KetToan getOne(String maKetToan);

    ArrayList<KetToan> getAll();

    ArrayList<KetToan> getByDate(Date start, Date end);
=======
    KetToan getOne(String maKetToan) throws Exception;
     ArrayList<KetToan> getAll()throws Exception;
    ArrayList<KetToan> getByDate(Date start, Date end) throws Exception;
>>>>>>> 958cd70d04bbf44f3c05477c0e0cf8618cd0f04b
}
