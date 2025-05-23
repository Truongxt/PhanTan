/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ChiTietDoiTra;
import entity.DoiTra;
import entity.Thuoc;
import interfaces.IChiTietDoiTra;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HÀ NHƯ
 */
public class ChiTietDoiTra_DAO extends UnicastRemoteObject implements IChiTietDoiTra {
    private EntityManagerFactory emf;
    public ChiTietDoiTra_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public List<ChiTietDoiTra> getAll() {
        var em = emf.createEntityManager();
        try {
            List<ChiTietDoiTra> resultList = em.createQuery("SELECT c FROM ChiTietDoiTra c", ChiTietDoiTra.class).getResultList();
            return new ArrayList<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ChiTietDoiTra getOne(String maHDDT, String maThuoc) throws Exception {
        var em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM ChiTietDoiTra c WHERE c.doiTra.maHDDT = :maHDDT AND c.Thuoc.maThuoc = :maThuoc",
                            ChiTietDoiTra.class)
                    .setParameter("maHDDT", maHDDT)
                    .setParameter("maThuoc", maThuoc)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(ChiTietDoiTra chiTietDoiTra) throws Exception {
        var em = emf.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(chiTietDoiTra);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean updateProduct(String id, int soLuong) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Thuoc thuoc = em.find(Thuoc.class, id);
            if (thuoc != null) {
                int soLuongMoi = thuoc.getSoLuongTon() - soLuong;
                thuoc.setSoLuongTon(soLuongMoi);
                em.merge(thuoc);
            }
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
    public Boolean updateRefund(ChiTietDoiTra chiTietDoiTra) throws Exception {
        return false;
    }

    @Override
    public List<ChiTietDoiTra> getAllForOrderReturnID(String returnOrderID) throws Exception {
        var em = emf.createEntityManager();
        try {
            String jpql = "SELECT c FROM ChiTietDoiTra c JOIN FETCH c.thuoc WHERE c.doiTra.maHDDT = :maHDDT";
            TypedQuery<ChiTietDoiTra> query = em.createQuery(jpql, ChiTietDoiTra.class);
            query.setParameter("maHDDT", returnOrderID);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    @Override
    public void createReturnOrderDetail(DoiTra newReturnOrder, ArrayList<ChiTietDoiTra> cart) throws Exception {
        for (ChiTietDoiTra chiTietDoiTra : cart) {
            chiTietDoiTra.setDoiTra(newReturnOrder);
            create(chiTietDoiTra);
        }
    }

    @Override
    public List<ChiTietDoiTra> getReturnedAndExchangedDrugs() throws Exception {
        var em = emf.createEntityManager();
        try {
            String jpql = "SELECT c FROM ChiTietDoiTra c " +
                    "JOIN FETCH c.Thuoc t " +
                    "JOIN FETCH c.doiTra d";
            return em.createQuery(jpql, ChiTietDoiTra.class).getResultList();
        } finally {
            em.close();
        }
    }

//    public ChiTietDoiTra getOne(String maHDDT, String maThuoc) {
//        ChiTietDoiTra chiTietDoiTra = null;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM ChiTietDoiTra "
//                    + "WHERE maHoaDonDoiTra = ? AND maThuoc = ?");
//            st.setString(1, maHDDT);
//            st.setString(2, maThuoc);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                maHDDT = rs.getString("maHoaDonDoiTra");
//                maThuoc = rs.getString("maThuoc");
//                int soLuong = rs.getInt("soLuong");
//                double gia = rs.getDouble("donGia");
//                DoiTra doiTra = new DoiTra(maHDDT);
//                Thuoc product = new Thuoc(maThuoc);
//                chiTietDoiTra = new ChiTietDoiTra(doiTra, product, soLuong, gia);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return chiTietDoiTra;
//    }
//
////    @Override
//    public ArrayList<ChiTietDoiTra> getAll() {
//        ArrayList result = new ArrayList<>();
//        try {
//            Statement st = ConnectDB.conn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietDoiTra");
//
//            while (rs.next()) {
//                String maHDDT = rs.getString("maHoaDonDoiTra");
//                String maThuoc = rs.getString("maThuoc");
//                int soLuong = rs.getInt("soLuong");
//                DoiTra doiTra = new DoiTra(maHDDT);
//                Thuoc thuoc = new Thuoc(maThuoc);
//                ChiTietDoiTra chiTietDoiTra = new ChiTietDoiTra(doiTra, thuoc, soLuong, thuoc.getGia());
//                result.add(chiTietDoiTra);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static Boolean create(ChiTietDoiTra chiTietDoiTra) {
//        int n = 0;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("INSERT INTO ChiTietDoiTra(maHoaDonDoiTra, maThuoc, soLuong, donGia)  "
//                    + "VALUES (?,?, ?, ?)");
//            st.setString(1, chiTietDoiTra.getDoiTra().getMaHDDT());
//            st.setString(2, chiTietDoiTra.getSanPham().getMaThuoc());
//            st.setInt(3, chiTietDoiTra.getSoLuong());
//            st.setDouble(4, chiTietDoiTra.getGia());
//            n = st.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }
//
//    public Boolean updateProduct(String id, int soLuong) {
//        int n = 0;
//        Thuoc product = new Thuoc_DAO().getThuoc(id);
//        int newSoluong = product.getSoLuongTon()- soLuong;
//        try {
//            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE Thuoc "
//                    + "SET soLuongTon = ? "
//                    + "WHERE maThuoc = ?");
//            st.setInt(1, newSoluong);
//            st.setString(2, id);
//            n = st.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }
//    public Boolean updateRefund(ChiTietDoiTra chiTietDoiTra) {
//        int n = 0;
//
//        return n > 0;
//    }
//
//    public ArrayList<ChiTietDoiTra> getAllForOrderReturnID(String returnOrderID) {
//    ArrayList<ChiTietDoiTra> result = new ArrayList<>();
//    String sql = "SELECT ctdt.maThuoc, ctdt.soLuong, ctdt.donGia, t.tenThuoc " +
//                 "FROM ChiTietDoiTra ctdt " +
//                 "JOIN Thuoc t ON ctdt.maThuoc = t.maThuoc " +
//                 "WHERE ctdt.maHoaDonDoiTra = ?";
//
//    try {
//        PreparedStatement st = ConnectDB.conn.prepareStatement(sql);
//        st.setString(1, returnOrderID);
//        ResultSet rs = st.executeQuery();
//
//        while (rs.next()) {
//            String maThuoc = rs.getString("maThuoc");
//            String tenThuoc = rs.getString("tenThuoc");
//            int soLuong = rs.getInt("soLuong");
//            double donGia = rs.getDouble("donGia");
//            Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, donGia);
//            DoiTra doiTra = new DoiTra(returnOrderID);
//            ChiTietDoiTra chiTietDoiTra = new ChiTietDoiTra(doiTra, thuoc, soLuong, donGia);
//            result.add(chiTietDoiTra);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return result;
//}
//
//
//    public static void createReturnOrderDetail(DoiTra newReturnOrder, ArrayList<ChiTietDoiTra> cart) {
//        for (ChiTietDoiTra chiTietDoiTra : cart) {
//            try {
//                chiTietDoiTra.setReturnOrder(newReturnOrder);
//                ChiTietDoiTra_DAO.create(chiTietDoiTra);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//
//            }
//        }
//    }
//
//    public ArrayList<ChiTietDoiTra> getReturnedAndExchangedDrugs() {
//        ArrayList<ChiTietDoiTra> result = new ArrayList<>();
//        String query = "SELECT ctdt.maThuoc, t.tenThuoc, ctdt.soLuong, ctdt.donGia, hd.ngayDoiTra, hd.loaiDoiTra, hd.maHoaDonDoiTra, hd.moTa "
//                + "FROM ChiTietDoiTra ctdt "
//                + "JOIN Thuoc t ON ctdt.maThuoc = t.maThuoc "
//                + "JOIN HoaDonDoiTra hd ON ctdt.maHoaDonDoiTra = hd.maHoaDonDoiTra";
//
//        try {
//            // Sử dụng Connection đã được khởi tạo trong ConnectDB
//            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                String maThuoc = rs.getString("maThuoc");
//                String tenThuoc = rs.getString("tenThuoc");
//                int soLuong = rs.getInt("soLuong");
//                double gia = rs.getDouble("donGia");
//                Date ngayDoiTra = rs.getDate("ngayDoiTra");
//                boolean loaiDoiTra = rs.getBoolean("loaiDoiTra");
//                String maHoaDonDoiTra = rs.getString("maHoaDonDoiTra");
//                String moTa = rs.getString("moTa");
//
//                Thuoc sanPham = new Thuoc(maThuoc, tenThuoc, gia);
//                DoiTra doiTra = new DoiTra(maHoaDonDoiTra);
//
//                ChiTietDoiTra chiTietDoiTra = new ChiTietDoiTra(doiTra, sanPham, soLuong, gia);
//                chiTietDoiTra.getDoiTra().setNgayDoiTra(ngayDoiTra);
//                chiTietDoiTra.getDoiTra().setLoai(loaiDoiTra);
//                chiTietDoiTra.getDoiTra().setLiDO(moTa);
//
//                result.add(chiTietDoiTra);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
