package dao;

import entity.TaiKhoan;
import interfaces.ITaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoan_DAO extends UnicastRemoteObject implements ITaiKhoan {

    private static final Logger LOGGER = Logger.getLogger(TaiKhoan_DAO.class.getName());
    private final EntityManagerFactory emf;

    public TaiKhoan_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public boolean create(TaiKhoan tk) throws RemoteException {
        if (tk == null || tk.getTenTaiKhoan() == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tk);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi tạo tài khoản: " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<TaiKhoan> getAllTaiKhoan() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            List<TaiKhoan> result = em.createQuery("SELECT t FROM TaiKhoan t", TaiKhoan.class)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách tài khoản: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public TaiKhoan getTaiKhoan(String ten) throws RemoteException {
        if (ten == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            TaiKhoan taiKhoan = em.find(TaiKhoan.class, ten);
            return taiKhoan;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy tài khoản với tenTaiKhoan = " + ten + ": " + e.getMessage(), e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateTaiKhoan(TaiKhoan tk) throws RemoteException {
        if (tk == null || tk.getTenTaiKhoan() == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TaiKhoan existingTaiKhoan = em.find(TaiKhoan.class, tk.getTenTaiKhoan());
            if (existingTaiKhoan != null) {
                existingTaiKhoan.setPassword(tk.getPassword());
                existingTaiKhoan.setMaVaiTro(tk.getMaVaiTro());
                existingTaiKhoan.setMaNhanVien(tk.getMaNhanVien());
                em.merge(existingTaiKhoan);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật tài khoản: " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteTaiKhoan(String ten) throws RemoteException {
        if (ten == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TaiKhoan taiKhoan = em.find(TaiKhoan.class, ten);
            if (taiKhoan != null) {
                em.remove(taiKhoan);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa tài khoản với tenTaiKhoan = " + ten + ": " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean doiMatKHau(String ten, String mk) throws RemoteException {
        if (ten == null || mk == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TaiKhoan taiKhoan = em.find(TaiKhoan.class, ten);
            if (taiKhoan != null) {
                taiKhoan.setPassword(mk);
                em.merge(taiKhoan);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Lỗi khi đổi mật khẩu cho tenTaiKhoan = " + ten + ": " + e.getMessage(), e);
            return false;
        } finally {
            em.close();
        }
    }
}