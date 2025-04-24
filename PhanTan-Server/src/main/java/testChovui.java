import dao.HoaDon_DAO;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.rmi.RemoteException;

public class testChovui {
    public static void main(String[] args) throws Exception {
        HoaDon_DAO hoaDonDao = new HoaDon_DAO();
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien("NV250124001");
        nhanVien.setTenNhanVien("Nguyễn Văn A");
        System.out.println(hoaDonDao.createNewOrder(nhanVien));
    }
}
