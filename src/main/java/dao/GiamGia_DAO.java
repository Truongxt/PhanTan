package dao;

import entity.GiamGia;
import java.sql.PreparedStatement;
import connect.ConnectDB;
import entity.LoaiGiamGia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Xuân Trường
 */
public class GiamGia_DAO {

    public Boolean create(GiamGia gg) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("insert GiamGia values (?,?,?,?,?)");
            ps.setString(1, gg.getMaGiamGia());
            ps.setString(2, gg.getTenGiamGia());
            ps.setString(3, gg.getMoTa());
            ps.setString(4, gg.getDonVi());
            ps.setString(5, gg.getLoaiGiamGia().toString()); // Assuming LoaiGiamGia is an enum or string representation
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<GiamGia> getAllGiamGia() {
        ArrayList<GiamGia> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM GiamGia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maGG = rs.getString("maGiamGia");
                String tenGiamGia = rs.getString("tenGiamGia");
                String moTa = rs.getString("moTa");
                String donVi = rs.getString("donVi");
                LoaiGiamGia loaiGiamGia = new LoaiGiamGia(rs.getString("maLoaiGiamGia")); // Adjust based on your actual column type
                GiamGia gg = new GiamGia(maGG, tenGiamGia, moTa, donVi, loaiGiamGia); // Adjust constructor as needed
                list.add(gg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public GiamGia getGiamGia(String maGiamGia) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM GiamGia WHERE maGiamGia = ?");
            ps.setString(1, maGiamGia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tenGiamGia = rs.getString("tenGiamGia");
                String moTa = rs.getString("moTa");
                String donVi = rs.getString("donVi");
                LoaiGiamGia loaiGiamGia = new LoaiGiamGia(rs.getString("maLoaiGiamGia")); // Adjust based on your actual column type
                return new GiamGia(maGiamGia, tenGiamGia, moTa, donVi, loaiGiamGia); // Adjust constructor as needed
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateGiamGia(String maGiamGia, GiamGia newGG) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("UPDATE GiamGia SET "
                    + "tenGiamGia = ?, "
                    + "moTa = ?, "
                    + "donVi = ?, "
                    + "loaiGiamGia = ? "
                    + "WHERE maGiamGia = ?");
            ps.setString(1, newGG.getTenGiamGia());
            ps.setString(2, newGG.getMoTa());
            ps.setString(3, newGG.getDonVi());
            ps.setString(4, newGG.getLoaiGiamGia().toString()); // Adjust based on your actual type
            ps.setString(5, maGiamGia);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public int getSize() {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT COUNT(*) FROM GiamGia");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<GiamGia> searchByName(String tenGiamGia) {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM GiamGia WHERE tenGiamGia LIKE ?");
            ps.setString(1, "%" + tenGiamGia + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maGG = rs.getString("maGiamGia");
                String moTa = rs.getString("moTa");
                String donVi = rs.getString("donVi");
               LoaiGiamGia loaiGiamGia = new LoaiGiamGia(rs.getString("maLoaiGiamGia"));
                GiamGia gg = new GiamGia(maGG, tenGiamGia, moTa, donVi, loaiGiamGia); // Adjust constructor as needed
                listGG.add(gg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiamGia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGG;
    }
}
