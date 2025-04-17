package dao;

import entity.LoaiThuoc;
import java.sql.PreparedStatement;
import connect.ConnectDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Xuân Trường
 */
public class LoaiThuoc_DAO {

    public Boolean create(LoaiThuoc loaiThuoc) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO LoaiThuoc VALUES (?,?)");
            ps.setString(1, loaiThuoc.getMaLoai());
            ps.setString(2, loaiThuoc.getTenLoai());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<LoaiThuoc> getAllLoaiThuoc() {
        ArrayList<LoaiThuoc> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiThuoc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                LoaiThuoc loaiThuoc = new LoaiThuoc(maLoai, tenLoai);
                list.add(loaiThuoc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThuoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public LoaiThuoc getLoaiThuoc(String maLoai) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiThuoc WHERE maLoai = ?");
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tenLoai = rs.getString("tenLoai");
                return new LoaiThuoc(maLoai, tenLoai);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThuoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateLoaiThuoc(String maLoai, LoaiThuoc newLoaiThuoc) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE LoaiThuoc SET tenLoai = ? WHERE maLoai = ?");
            st.setString(1, newLoaiThuoc.getTenLoai());
            st.setString(2, maLoai);
            n = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThuoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public int getSize() {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT COUNT(*) FROM LoaiThuoc");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThuoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<LoaiThuoc> searchByMaLoai(String maLoai) {
        ArrayList<LoaiThuoc> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiThuoc WHERE maLoai LIKE ?");
            ps.setString(1, "%" + maLoai + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                list.add(new LoaiThuoc(ma, tenLoai));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThuoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<LoaiThuoc> searchByTenLoai(String tenLoai) {
        ArrayList<LoaiThuoc> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM LoaiThuoc WHERE tenLoai LIKE ?");
            ps.setString(1, "%" + tenLoai + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String ten = rs.getString("tenLoai");
                list.add(new LoaiThuoc(maLoai, ten));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThuoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
