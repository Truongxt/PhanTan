/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BangKiemTien;
import java.util.ArrayList;
import java.util.Collections;
import utilities.CashCountSheetPrinter;

/**
 *
 * @author lemin
 */
public class DanhSachPhieuKiemTien_DAO {

    private BangKiemTien_DAO bangKiemTien_DAO = new BangKiemTien_DAO();

    public ArrayList<BangKiemTien> getAll() {
        ArrayList<BangKiemTien> list = bangKiemTien_DAO.getAll();
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public BangKiemTien getOne(String id) {
        return bangKiemTien_DAO.getOne(id);
    }
}
