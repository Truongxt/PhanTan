package dao;

import entity.NhanVien;
import entity.Otp;
import interfaces.IOTP;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OTP_DAO extends UnicastRemoteObject implements IOTP {

    private final EntityManagerFactory emf;

    public OTP_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
        scheduleDeleteOTP60s(); // Start scheduler on initialization
    }

    @Override
    public String getOtpFromDatabase(String email) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            Otp otp = em.createQuery("SELECT o FROM Otp o WHERE o.tentaiKhoan.email = :email", Otp.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return otp.getMaXacNhan();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Error retrieving OTP from database: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean saveOtpToDatabase(String tenTaiKhoan, String otp) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            NhanVien nhanVien = em.createQuery("SELECT n FROM NhanVien n WHERE n.email = :tenTaiKhoan", NhanVien.class)
                    .setParameter("tenTaiKhoan", tenTaiKhoan)
                    .getSingleResult();
            if (nhanVien == null) {
                System.err.println("NhanVien not found for tenTaiKhoan: " + tenTaiKhoan);
                return false;
            }

            Otp existingOtp = null;
            try {
                existingOtp = em.createQuery("SELECT o FROM Otp o WHERE o.tentaiKhoan.email = :tenTaiKhoan", Otp.class)
                        .setParameter("tenTaiKhoan", tenTaiKhoan)
                        .getSingleResult();
            } catch (NoResultException ignored) {
            }

            if (existingOtp != null) {
                existingOtp.setMaXacNhan(otp);
                existingOtp.setCreatedAt(LocalDateTime.now());
                em.merge(existingOtp);
            } else {
                Otp otpEntity = new Otp();
                otpEntity.setTentaiKhoan(nhanVien);
                otpEntity.setMaXacNhan(otp);
                otpEntity.setCreatedAt(LocalDateTime.now());
                em.persist(otpEntity);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error saving OTP to database: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteOtpFromDatabase(String email) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Otp otp = em.createQuery("SELECT o FROM Otp o WHERE o.tentaiKhoan.email = :email", Otp.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (otp != null) {
                em.remove(otp);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error deleting OTP from database: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public String getMaXacNhan(String ten) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            Otp otp = em.createQuery("SELECT o FROM Otp o WHERE o.tentaiKhoan.email = :ten", Otp.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
            return otp.getMaXacNhan();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Error retrieving confirmation code: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteExpiredOTPs() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            int deletedRows = em.createQuery("DELETE FROM Otp o WHERE o.createdAt < :expiryTime")
                    .setParameter("expiryTime", LocalDateTime.now().minusSeconds(60))
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("Deleted " + deletedRows + " expired OTP(s).");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error deleting expired OTPs: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteOTP60s() throws RemoteException {
        scheduleDeleteOTP60s();
    }

    private void scheduleDeleteOTP60s() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                deleteExpiredOTPs();
            } catch (RemoteException e) {
                System.err.println("Error running OTP deletion scheduler: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}