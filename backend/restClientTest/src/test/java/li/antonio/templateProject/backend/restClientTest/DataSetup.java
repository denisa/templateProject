package li.antonio.templateProject.backend.restClientTest;

import org.junit.Test;

import li.antonio.templateProject.backend.atlas.Country;

public class DataSetup extends DatabaseConnection {

    @Test
    public void seedDataBase() {
        entityManager.persist(new Country("France"));
        entityManager.persist(new Country("Suisse"));
    }

}
