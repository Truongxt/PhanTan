// src/main/java/crud/test_Thuoc.java
package crud;

import dao.Thuoc_DAO;
import entity.Thuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class test_Thuoc {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();
            EntityTransaction tr = em.getTransaction();

            Thuoc_DAO thuocDAO = new Thuoc_DAO(em);

            // Tạo một Thuoc mới
            Thuoc newThuoc = new Thuoc();
            newThuoc.setMaThuoc("T-001");
            newThuoc.setTenThuoc("Paracetamol");
            newThuoc.setGia(5000.0);


            thuocDAO.create(newThuoc);

            System.out.println("Thuoc moi da duoc tao: " + newThuoc);

            // Cập nhật Thuoc
            newThuoc.setTenThuoc("Paracetamol 500mg");
            newThuoc.setGia(5500.0);


            thuocDAO.update(newThuoc);

            System.out.println("Thuoc da duoc cap nhat: " + newThuoc);

            // Xóa Thuoc

            thuocDAO.delete(newThuoc.getMaThuoc());

            System.out.println("Thuoc da duoc xoa: " + newThuoc.getMaThuoc());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}