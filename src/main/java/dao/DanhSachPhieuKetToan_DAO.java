/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.KetToan;
import interfaces.IDanhSachPhieuKetToan;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author lemin
 */
public class DanhSachPhieuKetToan_DAO extends UnicastRemoteObject implements IDanhSachPhieuKetToan {

    private KetToan_DAO ketToan_DAO;
    private BangKiemTien_DAO bangKiemTien_DAO;

    public DanhSachPhieuKetToan_DAO() throws Exception {
        super();
        ketToan_DAO = new KetToan_DAO();
        bangKiemTien_DAO = new BangKiemTien_DAO();
    }

    @Override
    public KetToan getOne(String maKetToan) {
        return ketToan_DAO.getOne(maKetToan);
    }

    @Override
    public ArrayList<KetToan> getAll() throws Exception {
        ArrayList<KetToan> list = new ArrayList<>();
        for (KetToan ketToan : ketToan_DAO.getAll()) {
            ketToan.setMaBangKiemTien(bangKiemTien_DAO.getOne(ketToan.getMaBangKiemTien().getMaBangKiemTien()));
            list.add(ketToan);
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    @Override
    public ArrayList<KetToan> getByDate(Date start, Date end) throws Exception {
        ArrayList<KetToan> list = getAll();
        return list;
    }
}
