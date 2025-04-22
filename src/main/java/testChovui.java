import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class testChovui {
    public static void main(String[] args) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("default");
    }
}
