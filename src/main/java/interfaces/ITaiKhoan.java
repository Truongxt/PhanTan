package interfaces;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ITaiKhoan extends Remote {
    boolean create(TaiKhoan tk);
    ArrayList<TaiKhoan> getAllTaiKhoan();
    TaiKhoan getTaiKhoan(String ten);
    boolean updateTaiKhoan(TaiKhoan tk);
    boolean deleteTaiKhoan(String ten);
    boolean doiMatKHau(String ten, String mk);
}
