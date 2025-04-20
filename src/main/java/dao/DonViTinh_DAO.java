package dao;

import entity.DonViTinh;
import interfaces.IDonViTinh;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DonViTinh_DAO extends UnicastRemoteObject implements IDonViTinh {

    private final EntityManagerFactory emf;

    public DonViTinh_DAO() throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public Optional<DonViTinh> findById(String maDonViTinh) throws Exception {
        var em = emf.createEntityManager();
        try {
            DonViTinh dvt = em.find(DonViTinh.class, maDonViTinh);
            return Optional.ofNullable(dvt);
        } finally {
            em.close();
        }
    }

    @Override
    public List<DonViTinh> findAll() throws Exception {
        var em = emf.createEntityManager();
        try {
            List<DonViTinh> result = em.createQuery("SELECT d FROM DonViTinh d", DonViTinh.class).getResultList();
            return new ArrayList<>(result);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(DonViTinh donViTinh) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(donViTinh);
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
    public boolean update(DonViTinh donViTinh) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            DonViTinh existingEntity = em.find(DonViTinh.class, donViTinh.getMaDonViTinh());
            if (existingEntity != null) {
                existingEntity.setTen(donViTinh.getTen());
                em.merge(existingEntity);
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
    public boolean delete(String maDonViTinh) throws Exception {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            DonViTinh existingEntity = em.find(DonViTinh.class, maDonViTinh);
            if (existingEntity != null) {
                em.remove(existingEntity);
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
}