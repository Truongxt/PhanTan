/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BangKiemTien;
import entity.ChiTietBangKiemTien;
import entity.ChiTietBangKiemTienId;
import entity.NhanVien;
import interfaces.IChiTietBangKiemTien;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChiTietBangKiemTien_DAO extends UnicastRemoteObject implements IChiTietBangKiemTien {

    private NhanVien_DAO nhanVien_DAO ;
    private EntityManagerFactory emf;

    public ChiTietBangKiemTien_DAO() throws RemoteException {
        super();
        nhanVien_DAO = new NhanVien_DAO();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public ChiTietBangKiemTien getOne(String maBangKiemTien) throws Exception {
        var em = emf.createEntityManager();
        try {
            return em.find(ChiTietBangKiemTien.class, maBangKiemTien);
    } finally {
        em.close();
    }

    }

    @Override
    public List<ChiTietBangKiemTien> findAll() throws Exception {
        var em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM BangKiemTien b", ChiTietBangKiemTien.class).getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public ArrayList<ChiTietBangKiemTien> getAllCashCountSheetDetailInCashCountSheet(String maBangKiemTien) {
        var em = emf.createEntityManager();
        try {
            List<ChiTietBangKiemTien> result = em.createQuery(
                            "SELECT c FROM ChiTietBangKiemTien c WHERE c.bangKiemTien.maBangKiemTien = :ma", ChiTietBangKiemTien.class)
                    .setParameter("ma", maBangKiemTien)
                    .getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(ChiTietBangKiemTien chiTietBangKiemTien) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(chiTietBangKiemTien);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(ChiTietBangKiemTienId id) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ChiTietBangKiemTien existingEntity = em.find(ChiTietBangKiemTien.class, id);
            if (existingEntity != null) {
                em.remove(existingEntity);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

//    public ChiTietBangKiemTien getOne(String maBangKiemTien) {
//        ChiTietBangKiemTien chiTietBangKiemTien = null;
//
//        try {
//            String sql = "SELECT * FROM ChiTietBangKiemTien WHERE maBangKiemTien = ?";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//            preparedStatement.setString(1, maBangKiemTien);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                boolean chiSo = resultSet.getBoolean("chiSo");
//                String maNhanVien = resultSet.getString("maNhanVien");
//
//                chiTietBangKiemTien = new ChiTietBangKiemTien(chiSo, new NhanVien(maNhanVien), new BangKiemTien(maBangKiemTien));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return chiTietBangKiemTien;
//    }
//
//    public ArrayList<ChiTietBangKiemTien> getAllCashCountSheetDetailInCashCountSheet(String maBangKiemTien) {
//        ArrayList<ChiTietBangKiemTien> chiTietBangKiemTiens = new ArrayList<>();
//
//        try {
//            String sql = "SELECT * FROM ChiTietBangKiemTien WHERE maBangKiemTien = ?";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//            preparedStatement.setString(1, maBangKiemTien);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                boolean chiSo = resultSet.getBoolean("chiSoKiem");
//                String maNhanVien = resultSet.getString("maNhanVien");
//
//                NhanVien nhanVien = nhanVien_DAO.getNhanVien(maNhanVien);
//                BangKiemTien bangKiemTien = new BangKiemTien(maBangKiemTien);
//
//                ChiTietBangKiemTien chiTietBangKiemTien = new ChiTietBangKiemTien(chiSo, nhanVien, bangKiemTien);
//                chiTietBangKiemTiens.add(chiTietBangKiemTien);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return chiTietBangKiemTiens;
//    }
//
//    public Boolean create(ChiTietBangKiemTien chiTietBangKiemTien) {
//        try {
//            String sql = "INSERT INTO ChiTietBangKiemTien (chiSoKiem, maBangKiemTien, maNhanVien) VALUES (?, ?, ?)";
//            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
//
//            preparedStatement.setBoolean(1, chiTietBangKiemTien.getChiSo());
//            preparedStatement.setString(2, chiTietBangKiemTien.getBangKiemTien().getMaBangKiemTien());
//            preparedStatement.setString(3, chiTietBangKiemTien.getNhanVien().getMaNhanVien());
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                return true; // Thành công
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false; // Thất bại
//    }

}
