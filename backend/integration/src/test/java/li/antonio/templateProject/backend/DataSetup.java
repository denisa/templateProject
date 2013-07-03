package li.antonio.templateProject.backend;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataSetup {
    private static final String BASE_URL = "jdbc:derby:" + "directory:integrationDB";
    private static final String DERBY_SYSTEM_HOME = "derby.system.home";

    private EntityManagerFactory emFactory;
    private EntityManager entityManager;

    @Before
    public void openDataBase() throws SQLException {
        System.setProperty(DERBY_SYSTEM_HOME, "target/derby");
        DriverManager.getConnection(BASE_URL + ";create=true").close();

        final Map<String, String> persistenceProperties = new HashMap<>();
        persistenceProperties.put("javax.persistence.jdbc.url", BASE_URL);
        persistenceProperties.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
        persistenceProperties.put("javax.persistence.jdbc.user", "");
        persistenceProperties.put("javax.persistence.jdbc.password", "");
        persistenceProperties.put("javax.persistence.schema-generation.database.action", "create");

        emFactory = Persistence.createEntityManagerFactory("test-pu", persistenceProperties);
        entityManager = emFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @Test
    public void seedDataBase() {
        entityManager.persist(new li.antonio.templateProject.endpoints.Country("France"));
        entityManager.persist(new li.antonio.templateProject.endpoints.Country("Suisse"));
    }

    @After
    public void closeDataBase() {
        if (entityManager != null) {
            final EntityTransaction transaction = entityManager.getTransaction();
            if (transaction.isActive()) {
                if (transaction.getRollbackOnly()) {
                    transaction.rollback();
                } else {
                    transaction.commit();
                }
            }
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        if (emFactory != null) {
            emFactory.close();
        }
        try {
            DriverManager.getConnection(BASE_URL + ";shutdown=true").close();
        } catch (SQLException ex) {
            // XJ015 (with SQLCODE 50000) is the expected SQLSTATE for complete system shutdown.
            // 08006 (with SQLCODE 45000) is the expected SQLSTATE for shutdown of only an individual database.
            if (ex.getErrorCode() != 45000 && ex.getErrorCode() != 50000) {
                Assert.fail("Failing to close Derby: " + ex.getMessage());
            }
        } finally {
            System.getProperties().remove(DERBY_SYSTEM_HOME);
        }
    }

}
