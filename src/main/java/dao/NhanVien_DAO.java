package dao;

import entity.NhanVien;
import interfaces.INhanVien;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NhanVien_DAO extends UnicastRemoteObject implements INhanVien {

    private final EntityManagerFactory em;

    public NhanVien_DAO() throws RemoteException {
        super();
        em = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public Boolean create(NhanVien nv) {
        var entityManager = em.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nv);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error creating NhanVien", e);
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ArrayList<NhanVien> getAllNhanVien() {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv", NhanVien.class);
            return new ArrayList<>(query.getResultList());
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error retrieving all NhanVien", ex);
        }
        return new ArrayList<>();
    }

    @Override
    public NhanVien getNhanVien(String maNhanVien) {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien = :maNhanVien", NhanVien.class);
            query.setParameter("maNhanVien", maNhanVien);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null; // Return null if no result is found
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error retrieving NhanVien by ID", ex);
        }
        return null;
    }

    @Override
    public boolean suaNhanVien(String maNhanVien, NhanVien newNV) {
        var entityManager = em.createEntityManager();
        try {
            NhanVien nv = entityManager.find(NhanVien.class, maNhanVien);
            if (nv != null) {
                entityManager.getTransaction().begin();
                nv.setTenNhanVien(newNV.getTenNhanVien());
                nv.setEmail(newNV.getEmail());
                nv.setSdt(newNV.getSdt());
                nv.setDiaChi(newNV.getDiaChi());
                nv.setCccd(newNV.getCccd());
                nv.setTrangThai(newNV.getTrangThai());
                nv.setNgayVaoLam(newNV.getNgayVaoLam());
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception ex) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error updating NhanVien", ex);
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public int getSize() {
        try {
            TypedQuery<Long> query = em.createEntityManager().createQuery("SELECT COUNT(nv) FROM NhanVien nv", Long.class);
            return query.getSingleResult().intValue();
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error getting size of NhanVien", ex);
        }
        return 0;
    }

    @Override
    public NhanVien getLastNhanVien() {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv ORDER BY nv.maNhanVien DESC", NhanVien.class);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error retrieving last NhanVien", ex);
        }
        return null;
    }

    @Override
    public ArrayList<NhanVien> timKiemTheoMa(String maNhanVien) {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien LIKE :maNhanVien", NhanVien.class);
            query.setParameter("maNhanVien", "%" + maNhanVien + "%");
            return new ArrayList<>(query.getResultList());
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error searching NhanVien by ID", ex);
        }
        return new ArrayList<>();
    }

    @Override
    public NhanVien timKiemTheoMa1(String maNhanVien) {
        return getNhanVien(maNhanVien); // Reuse getNhanVien method
    }

    @Override
    public ArrayList<NhanVien> timKiemTheoTen(String ten) {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv WHERE nv.tenNhanVien LIKE :ten", NhanVien.class);
            query.setParameter("ten", "%" + ten + "%");
            return new ArrayList<>(query.getResultList());
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error searching NhanVien by name", ex);
        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<NhanVien> timKiemTheoTrangThai(boolean tt) {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv WHERE nv.trangThai = :tt", NhanVien.class);
            query.setParameter("tt", tt);
            return new ArrayList<>(query.getResultList());
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error searching NhanVien by status", ex);
        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<NhanVien> timTheoSDT(String soDT) {
        try {
            TypedQuery<NhanVien> query = em.createEntityManager().createQuery("SELECT nv FROM NhanVien nv WHERE nv.sdt LIKE :soDT", NhanVien.class);
            query.setParameter("soDT", "%" + soDT + "%");
            return new ArrayList<>(query.getResultList());
        } catch (Exception ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error searching NhanVien by phone number", ex);
        }
        return new ArrayList<>();
    }

    @Override
    public String generateID() {
        String result = "NV";
        LocalDate time = LocalDate.now();
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("ddMMyy");
        result += dateFormater.format(time);

        try {
            TypedQuery<String> query = em.createEntityManager().createQuery("SELECT nv.maNhanVien FROM NhanVien nv WHERE nv.maNhanVien LIKE :prefix ORDER BY nv.maNhanVien DESC", String.class);
            query.setParameter("prefix", result + "%");
            query.setMaxResults(1);

            List<String> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                String lastID = resultList.get(0);
                String sNumber = lastID.substring(lastID.length() - 2);
                int num = Integer.parseInt(sNumber) + 1;
                result += String.format("%03d", num);
            } else {
                result += String.format("%03d", 0);
            }
        } catch (Exception e) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, "Error generating NhanVien ID", e);
        }

        return result;
    }
}
