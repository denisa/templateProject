package li.antonio.templateProject.applicationTest;

import org.junit.Test;

import li.antonio.templateProject.backend.atlas.Country;
import li.antonio.templateProject.backend.restClientTest.DatabaseConnection;

public class DataSetup extends DatabaseConnection {
    @Test
    public void seedDataBase() {
        entityManager.persist(new Country("Spain"));
        entityManager.persist(new Country("Italy"));
    }
}
