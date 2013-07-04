package li.antonio.templateProject.integration;

import org.junit.Test;

import li.antonio.templateProject.backend.atlas.Country;
import li.antonio.templateProject.backend.integration.DatabaseConnection;

public class DataSetup extends DatabaseConnection {
    @Test
    public void seedDataBase() {
        entityManager.persist(new Country("Spain"));
        entityManager.persist(new Country("Italy"));
    }
}
