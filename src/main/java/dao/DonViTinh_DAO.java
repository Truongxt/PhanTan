package dao;

import entity.DonViTinh;
import connect.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Xuân Trường
 */
public class DonViTinh_DAO {

    public boolean create(DonViTinh dvt) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO DonViTinh VALUES (?, ?)");
            ps.setString(1, dvt.getMaDonViTinh());
            ps.setString(2, dvt.getTen());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<DonViTinh> getAllDonViTinh() {
        ArrayList<DonViTinh> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM DonViTinh");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDonViTinh = rs.getString("maDonViTinh");
                String ten = rs.getString("ten");
                DonViTinh dvt = new DonViTinh(maDonViTinh, ten);
                list.add(dvt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinh_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public DonViTinh getDonViTinhById(String maDonViTinh) {
        DonViTinh dvt = null;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM DonViTinh WHERE maDonViTinh = ?");
            ps.setString(1, maDonViTinh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("ten");
                dvt = new DonViTinh(maDonViTinh, ten);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinh_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dvt;
    }

    public boolean updateDonViTinh(String maDonViTinh, DonViTinh newDonViTinh) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("UPDATE DonViTinh SET ten = ? WHERE maDonViTinh = ?");
            ps.setString(1, newDonViTinh.getTen());
            ps.setString(2, maDonViTinh);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinh_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public boolean deleteDonViTinh(String maDonViTinh) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("DELETE FROM DonViTinh WHERE maDonViTinh = ?");
            ps.setString(1, maDonViTinh);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinh_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public int getSize() {
        int count = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT COUNT(*) FROM DonViTinh");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinh_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
