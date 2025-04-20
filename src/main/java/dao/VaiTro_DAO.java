package dao;

import entity.VaiTro;
import interfaces.IVaiTro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaiTro_DAO extends UnicastRemoteObject implements IVaiTro {

    private static final Logger LOGGER = Logger.getLogger(VaiTro_DAO.class.getName());
    private final EntityManagerFactory emf;

    public VaiTro_DAO(EntityManager em) throws RemoteException {
        super();
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public ArrayList<VaiTro> getAllVaiTro() throws RemoteException {
        EntityManager em = emf.createEntityManager();
        try {
            List<VaiTro> result = em.createQuery("SELECT v FROM VaiTro v", VaiTro.class)
                    .getResultList();
            return new ArrayList<>(result);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách vai trò: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public VaiTro getVaiTro(String maVaiTro) throws RemoteException {
        if (maVaiTro == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            VaiTro vaiTro = em.find(VaiTro.class, maVaiTro);
            return vaiTro;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy vai trò với maVaiTro = " + maVaiTro + ": " + e.getMessage(), e);
            return null;
        } finally {
            em.close();
        }
    }
}