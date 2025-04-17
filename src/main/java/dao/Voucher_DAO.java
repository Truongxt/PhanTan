package dao;

import entity.Voucher;
import connect.ConnectDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Voucher_DAO {

    // Phương thức tạo mới một đối tượng Voucher
    public Boolean create(Voucher voucher) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO Voucher VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, voucher.getMaVoucher());
            ps.setString(2, voucher.getTenVoucher());
            ps.setString(3, voucher.getMoTa());
            ps.setDate(4, java.sql.Date.valueOf(voucher.getNgayApDung()));
            ps.setDate(5, java.sql.Date.valueOf(voucher.getNgayKetThuc()));
             ps.setDate(6, java.sql.Date.valueOf(voucher.getNgayKetThuc()));
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức lấy tất cả các đối tượng Voucher
    public ArrayList<Voucher> getAllVoucher() {
        ArrayList<Voucher> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Voucher";
            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maVoucher = rs.getString("maVoucher");
                String tenVoucher = rs.getString("tenVoucher");
                String moTa = rs.getString("moTa");

                Date ngayApDungDate = rs.getDate("ngayApDung");
                LocalDate ngayApDung = ngayApDungDate != null ? ngayApDungDate.toLocalDate() : null;

                Date ngayKetThucDate = rs.getDate("ngayKetThuc");
                LocalDate ngayKetThuc = ngayKetThucDate != null ? ngayKetThucDate.toLocalDate() : null;
                double giaGiam = rs.getDouble("giaGiam");

                Voucher voucher = new Voucher(maVoucher, tenVoucher, moTa, ngayApDung, ngayKetThuc,giaGiam);
                list.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức lấy một đối tượng Voucher theo mã
    public Voucher getVoucher(String maVoucher) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM Voucher WHERE maVoucher = ?");
            ps.setString(1, maVoucher);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tenVoucher = rs.getString("tenVoucher");
                String moTa = rs.getString("moTa");
                LocalDate ngayApDung = rs.getDate("ngayApDung").toLocalDate();
                LocalDate ngayKetThuc = rs.getDate("ngayKetThuc").toLocalDate();
                double giaGiam = rs.getDouble("giaGiam");
                return new Voucher(maVoucher, tenVoucher, moTa, ngayApDung, ngayKetThuc,giaGiam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật thông tin Voucher
    public boolean suaVoucher(String maVoucher, Voucher newVoucher) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement(
                "UPDATE Voucher SET tenVoucher = ?, moTa = ?, ngayApDung = ?, ngayKetThuc = ? WHERE maVoucher = ?");
            ps.setString(1, newVoucher.getTenVoucher());
            ps.setString(2, newVoucher.getMoTa());
            ps.setDate(3, java.sql.Date.valueOf(newVoucher.getNgayApDung()));
            ps.setDate(4, java.sql.Date.valueOf(newVoucher.getNgayKetThuc()));
            ps.setString(5, maVoucher);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức xóa Voucher
    public boolean xoaVoucher(String maVoucher) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("DELETE FROM Voucher WHERE maVoucher = ?");
            ps.setString(1, maVoucher);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
