/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BangKiemTien;
import interfaces.IDanhSachPhieuKiemTien;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lemin
 */
public class DanhSachPhieuKiemTien_DAO extends UnicastRemoteObject implements IDanhSachPhieuKiemTien {

    private BangKiemTien_DAO bangKiemTien_DAO;

    public DanhSachPhieuKiemTien_DAO() throws RemoteException {
        bangKiemTien_DAO = new BangKiemTien_DAO();
    }

    @Override
    public ArrayList<BangKiemTien> getAll() throws Exception {
        List<BangKiemTien> list = bangKiemTien_DAO.getAll();
        Collections.sort(list, Collections.reverseOrder());
        return list.stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public BangKiemTien getOne(String id) throws Exception {
        return bangKiemTien_DAO.getOne(id);
    }
}
