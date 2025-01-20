package dao;

import entity.Otp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class OTP_DAO {

    private EntityManager em;

    public OTP_DAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Otp> findById(String maOtp) {
        return Optional.ofNullable(em.find(Otp.class, maOtp));
    }

    public List<Otp> findAll() {
        TypedQuery<Otp> query = em.createQuery("SELECT o FROM Otp o", Otp.class);
        return query.getResultList();
    }

    public boolean create(Otp Otp) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(Otp);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(Otp Otp) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(Otp);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String maOtp) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Otp Otp = em.find(Otp.class, maOtp);
            if (Otp != null) {
                em.remove(Otp);
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