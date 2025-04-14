package dao;

import entity.Otp;
import interfaces.IOTP;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class OTP_DAO implements IOTP {

    private EntityManager em;

    public OTP_DAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Otp> findById(String maOtp) {
        return Optional.ofNullable(em.find(Otp.class, maOtp));
    }

    @Override
    public List<Otp> findAll() {
        TypedQuery<Otp> query = em.createQuery("SELECT o FROM Otp o", Otp.class);
        return query.getResultList();
    }

    @Override
    public boolean create(Otp otp) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(otp);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(Otp otp) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(otp);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String maOtp) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Otp otp = em.find(Otp.class, maOtp);
            if (otp != null) {
                em.remove(otp);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
}