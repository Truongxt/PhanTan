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
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OTP_DAO extends UnicastRemoteObject implements IOTP {

    private final EntityManagerFactory emf;

    public OTP_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
        scheduleDeleteOTP60s(); // Khởi động scheduler khi khởi tạo
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
            System.err.println("Lỗi khi lấy OTP từ cơ sở dữ liệu: " + e.getMessage());
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
            // Tìm NhanVien theo tenTaiKhoan (giả định tenTaiKhoan là email hoặc một định danh duy nhất)
            NhanVien nhanVien = em.createQuery("SELECT n FROM NhanVien n WHERE n.email = :tenTaiKhoan", NhanVien.class)
                    .setParameter("tenTaiKhoan", tenTaiKhoan)
                    .getSingleResult();
            if (nhanVien == null) {
                System.err.println("Không tìm thấy NhanVien với tenTaiKhoan: " + tenTaiKhoan);
                return false;
            }

            // Kiểm tra OTP hiện có
            Otp existingOtp = null;
            try {
                existingOtp = em.createQuery("SELECT o FROM Otp o WHERE o.tentaiKhoan.email = :tenTaiKhoan", Otp.class)
                        .setParameter("tenTaiKhoan", tenTaiKhoan)
                        .getSingleResult();
            } catch (NoResultException ignored) {
                // Không có OTP hiện có, tiếp tục tạo mới
            }

            if (existingOtp != null) {
                // Cập nhật OTP hiện có
                existingOtp.setMaXacNhan(otp);
                existingOtp.setCreatedAt(Instant.now());
                em.merge(existingOtp);
            } else {
                // Tạo OTP mới
                Otp otpEntity = new Otp();
                otpEntity.setTentaiKhoan(nhanVien);
                otpEntity.setMaXacNhan(otp);
                otpEntity.setCreatedAt(Instant.now());
                em.persist(otpEntity);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Lỗi khi lưu OTP vào cơ sở dữ liệu: " + e.getMessage());
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
            System.err.println("Lỗi khi xóa OTP khỏi cơ sở dữ liệu: " + e.getMessage());
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
            System.err.println("Lỗi khi lấy mã xác nhận: " + e.getMessage());
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
                    .setParameter("expiryTime", Instant.now().minusSeconds(60))
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("Deleted " + deletedRows + " expired OTP(s).");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Lỗi khi xóa OTP hết hạn: " + e.getMessage());
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
                System.err.println("Lỗi khi chạy lịch xóa OTP: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}