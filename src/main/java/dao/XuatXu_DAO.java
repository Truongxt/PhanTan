package dao;

import interfaces.IXuatXu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import entity.XuatXu;

import java.util.List;

@AllArgsConstructor
public class XuatXu_DAO implements IXuatXu {

    private EntityManager em;

    @Override
    public long countXuatXuByCountry(String country) {
        String query = "select count(*) from XuatXu where tenXuatXu = :country";
        Object obj = em.createQuery(query)
                .setParameter("country", country)
                .getSingleResult();
        return (long) obj;
    }

    @Override
    public List<XuatXu> listXuatXuByCountry(String country) {
        return em.createQuery("select x from XuatXu x where x.tenXuatXu like :country", XuatXu.class)
                .setParameter("country", "%" + country + "%")
                .getResultList();
    }

    @Override
    public XuatXu findById(String maXuatXu) {
        return em.find(XuatXu.class, maXuatXu);
    }

    @Override
    public boolean create(XuatXu xuatXu) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(xuatXu);
            tr.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean update(XuatXu xuatXu) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(xuatXu);
            tr.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            XuatXu xuatXu = em.find(XuatXu.class, id);
            em.remove(xuatXu);
            tr.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
}