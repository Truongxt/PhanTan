package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import entity.XuatXu;

import java.util.List;

@AllArgsConstructor
public class XuatXu_DAO {

    private EntityManager em;

    public long countXuatXuByCountry(String country) {
        String query = "select count(*) from XuatXu where tenXuatXu = :country";
        Object obj = em.createQuery(query)
                .setParameter("country", country)
                .getSingleResult();
        return (long) obj;
    }

    public List<XuatXu> listXuatXuByCountry(String country) {
        return em.createQuery("select x from XuatXu x where x.tenXuatXu like :country", XuatXu.class)
                .setParameter("country", "%" + country + "%")
                .getResultList();
    }

//    public List<XuatXu> listXuatXuByRange(int from, int to) {
//        String query = "select x from XuatXu x where x.range between :from and :to";
//        return em.createQuery(query, XuatXu.class)
//                .setParameter("from", from)
//                .setParameter("to", to)
//                .getResultList();
//    }

    public boolean save(XuatXu xuatXu) {
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