/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.NhanVien;
import entity.VaiTro;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xuân Trường
 */
public class VaiTro_DAO {
     public ArrayList<VaiTro> getAllVaiTro() {
        ArrayList<VaiTro> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from VaiTro");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maVT = rs.getString("maVaiTro");
                String tenVT = rs.getString("tenVaiTro");
                VaiTro vt = new VaiTro(maVT,tenVT);
                list.add(vt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public VaiTro getVaiTro (String maVaiTro){
         try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from VaiTro where maVaiTro = ?");
            ps.setString(1, maVaiTro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maVT = rs.getString("maVaiTro");
                String tenVT = rs.getString("tenVaiTro");
                return new VaiTro(maVT,tenVT);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
}
