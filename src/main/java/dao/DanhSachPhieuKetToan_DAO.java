/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.KetToan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author lemin
 */
public class DanhSachPhieuKetToan_DAO {

    private KetToan_DAO ketToan_DAO = new KetToan_DAO();
    private BangKiemTien_DAO bangKiemTien_DAO = new BangKiemTien_DAO();

    public KetToan getOne(String maKetToan) {
        return ketToan_DAO.getOne(maKetToan);
    }

    public ArrayList<KetToan> getAll() {
        ArrayList<KetToan> list = new ArrayList<>();
        for (KetToan ketToan : ketToan_DAO.getAll()) {
            ketToan.setBangKiemTien(bangKiemTien_DAO.getOne(ketToan.getBangKiemTien().getMaBangKiemTien()));
            list.add(ketToan);
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<KetToan> getByDate(Date start, Date end) {
        ArrayList<KetToan> list = getAll();
        return list;
    }
}
