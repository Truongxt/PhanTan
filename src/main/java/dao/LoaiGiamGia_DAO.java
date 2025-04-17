package dao;

import entity.LoaiGiamGia;
import java.sql.PreparedStatement;
import connect.ConnectDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Xuân Trường
 */
public class LoaiGiamGia_DAO {

    public Boolean create(LoaiGiamGia lg) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO LoaiGiamGia (maLoaiGiamGia, tenLoai) VALUES (?, ?)");
            ps.setString(1, lg.getMaLoaiGiamGia());
            ps.setString(2, lg.getTenLoai());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<LoaiGiamGia> getAllLoaiGiamGia() {
        ArrayList<LoaiGiamGia> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiGiamGia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoai = rs.getString("maLoaiGiamGia");
                String tenLoai = rs.getString("tenLoai");
                LoaiGiamGia lg = new LoaiGiamGia(maLoai, tenLoai); // Adjust constructor as needed
                list.add(lg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiGiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public LoaiGiamGia getLoaiGiamGia(String maLoaiGiamGia) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiGiamGia WHERE maLoaiGiamGia = ?");
            ps.setString(1, maLoaiGiamGia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tenLoai = rs.getString("tenLoai");
                return new LoaiGiamGia(maLoaiGiamGia, tenLoai); // Adjust constructor as needed
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiGiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateLoaiGiamGia(String maLoaiGiamGia, LoaiGiamGia newLG) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("UPDATE LoaiGiamGia SET "
                    + "tenLoai = ? "
                    + "WHERE maLoaiGiamGia = ?");
            ps.setString(1, newLG.getTenLoai());
            ps.setString(2, maLoaiGiamGia);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoaiGiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public int getSize() {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT COUNT(*) FROM LoaiGiamGia");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiGiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<LoaiGiamGia> searchByName(String tenLoai) {
        ArrayList<LoaiGiamGia> listLG = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiGiamGia WHERE tenLoai LIKE ?");
            ps.setString(1, "%" + tenLoai + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoai = rs.getString("maLoaiGiamGia");
                LoaiGiamGia lg = new LoaiGiamGia(maLoai, tenLoai); // Adjust constructor as needed
                listLG.add(lg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiGiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLG;
    }
}
