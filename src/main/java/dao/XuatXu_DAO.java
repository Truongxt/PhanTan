package dao;

import entity.XuatXu;
import connect.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Xuân Trường
 */
public class XuatXu_DAO {

    public boolean create(XuatXu xx) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO XuatXu VALUES (?, ?)");
            ps.setString(1, xx.getMaXuatXu());
            ps.setString(2, xx.getTen());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<XuatXu> getAllXuatXu() {
        ArrayList<XuatXu> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM XuatXu");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maXuatXu = rs.getString("maXuatXu");
                String ten = rs.getString("tenXuatXu");
                XuatXu xx = new XuatXu(maXuatXu, ten);
                list.add(xx);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public XuatXu getXuatXuById(String maXuatXu) {
        XuatXu xx = null;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM XuatXu WHERE maXuatXu = ?");
            ps.setString(1, maXuatXu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("tenXuatXu");
                xx = new XuatXu(maXuatXu, ten);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xx;
    }

    public boolean updateXuatXu(String maXuatXu, XuatXu newXuatXu) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("UPDATE XuatXu SET ten = ? WHERE maXuatXu = ?");
            ps.setString(1, newXuatXu.getTen());
            ps.setString(2, maXuatXu);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public boolean deleteXuatXu(String maXuatXu) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("DELETE FROM XuatXu WHERE maXuatXu = ?");
            ps.setString(1, maXuatXu);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public int getSize() {
        int count = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT COUNT(*) FROM XuatXu");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
