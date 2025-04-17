/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.KiemTien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lemin
 */
public class KiemTien_DAO {

    public KiemTien_DAO() {
    }

    public KiemTien getKiemTien(double giaTri, String maBangKiemTien) {
        KiemTien kiemTien = null;

        try {
            String sql = "SELECT * FROM KiemTien WHERE maBangKiemTien = ? AND value = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, maBangKiemTien);
            preparedStatement.setDouble(2, giaTri);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int soLuong = resultSet.getInt("soLuong");
                double tong = resultSet.getDouble("tong");

                kiemTien = new KiemTien(soLuong, giaTri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kiemTien;
    }

    public ArrayList<KiemTien> getAll(String maBangKiemTien) {
        ArrayList<KiemTien> kiemTiens = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KiemTien WHERE maBangKiemTien = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, maBangKiemTien);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double giaTri = resultSet.getDouble("giaTri");
                int soLuong = resultSet.getInt("soLuong");

                KiemTien kiemTien = new KiemTien(soLuong, giaTri);
                kiemTiens.add(kiemTien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kiemTiens;
    }

    public Boolean create(KiemTien kiemTien, String maBangKiemTien) {
        try {
            String sql = "INSERT INTO KiemTien (maBangKiemTien, giaTri, soLuong) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            preparedStatement.setString(1, maBangKiemTien);
            preparedStatement.setDouble(2, kiemTien.getGiaTri());
            preparedStatement.setInt(3, kiemTien.getSoLuong());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true; // Thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Thất bại
    }


    public double getTongTien(ArrayList<KiemTien> list) {
        double sum = 0;
        for (KiemTien kiemTien : list) {
            sum += kiemTien.getTong();
        }
        return sum;
    }

}
