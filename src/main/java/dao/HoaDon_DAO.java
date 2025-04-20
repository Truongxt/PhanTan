package dao;

import entity.HoaDon;
import interfaces.IHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HoaDon_DAO extends UnicastRemoteObject implements IHoaDon {

    private final EntityManagerFactory emf;

    public HoaDon_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public Optional<HoaDon> findById(String maHoaDon) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            HoaDon hoaDon = em.find(HoaDon.class, maHoaDon);
            return Optional.ofNullable(hoaDon);
        } finally {
            em.close();
        }
    }

    @Override
    public List<HoaDon> getAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            List<HoaDon> result = em.createQuery("SELECT h FROM HoaDon h", HoaDon.class).getResultList();
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> getAllHoaDon() throws RemoteException {
        try {
            return new ArrayList<>(getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public HoaDon getHoaDon(String maHD) throws RemoteException {
        try {
            return findById(maHD).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean deleteHoaDon(String maHD) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            HoaDon hoaDon = em.find(HoaDon.class, maHD);
            if (hoaDon != null) {
                em.remove(hoaDon);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public int getSize() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(h) FROM HoaDon h", Long.class).getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> getChiTietHoaDon(String maHD) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            List<HoaDon> result = em.createQuery(
                            "SELECT h FROM HoaDon h WHERE h.maHD = :maHD", HoaDon.class)
                    .setParameter("maHD", maHD)
                    .getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<HoaDon> filter(String maHD, String sdt, double doanhThu, LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT h FROM HoaDon h WHERE " +
                    "(:maHD IS NULL OR h.maHD LIKE :maHD) AND " +
                    "(:sdt IS NULL OR h.khachHang.sdt LIKE :sdt) AND " +
                    "(:doanhThu <= 0 OR h.tongTien >= :doanhThu) AND " +
                    "(:ngayBatDau IS NULL OR h.ngayLap >= :ngayBatDau) AND " +
                    "(:ngayKetThuc IS NULL OR h.ngayLap <= :ngayKetThuc)";
            List<HoaDon> result = em.createQuery(query, HoaDon.class)
                    .setParameter("maHD", maHD != null ? maHD + "%" : null)
                    .setParameter("sdt", sdt != null ? sdt + "%" : null)
                    .setParameter("doanhThu", doanhThu)
                    .setParameter("ngayBatDau", ngayBatDau)
                    .setParameter("ngayKetThuc", ngayKetThuc)
                    .getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }
}