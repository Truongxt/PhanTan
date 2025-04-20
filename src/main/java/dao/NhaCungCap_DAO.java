package dao;

import entity.NhaCungCap;
import interfaces.INhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhaCungCap_DAO extends UnicastRemoteObject implements INhaCungCap {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public NhaCungCap_DAO() throws Exception {
        super();
    }

    @Override
    public NhaCungCap create(NhaCungCap ncc) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ncc);
            em.getTransaction().commit();
            return ncc;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public NhaCungCap getById(String maNCC) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(NhaCungCap.class, maNCC);
        } catch (Exception e) {
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<NhaCungCap> getAll() {
        EntityManager em = emf.createEntityManager();
        List<NhaCungCap> result = new ArrayList<>();
        try {
            TypedQuery<NhaCungCap> query = em.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class);
            result = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public boolean update(String maNCC, NhaCungCap newNCC) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            NhaCungCap existingNCC = em.find(NhaCungCap.class, maNCC);
            if (existingNCC != null) {
                existingNCC.setTenNCC(newNCC.getTenNCC());
                existingNCC.setDiaChi(newNCC.getDiaChi());
                existingNCC.setEmail(newNCC.getEmail());
                existingNCC.setSdt(newNCC.getSdt());
                em.merge(existingNCC);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean delete(String maNCC) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            NhaCungCap ncc = em.find(NhaCungCap.class, maNCC);
            if (ncc != null) {
                em.remove(ncc);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<NhaCungCap> searchByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<NhaCungCap> result = new ArrayList<>();
        try {
            TypedQuery<NhaCungCap> query = em.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :name", NhaCungCap.class);
            query.setParameter("name", "%" + name + "%");
            result = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<NhaCungCap> searchByPhoneNumber(String phoneNumber) {
        EntityManager em = emf.createEntityManager();
        List<NhaCungCap> result = new ArrayList<>();
        try {
            TypedQuery<NhaCungCap> query = em.createQuery("SELECT n FROM NhaCungCap n WHERE n.sdt LIKE :phoneNumber", NhaCungCap.class);
            query.setParameter("phoneNumber", "%" + phoneNumber + "%");
            result = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return result;
    }
}
