package li.antonio.templateProject.endpoints;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("countries")
public class CountryResource {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    public List<Country> getAll() {
        return entityManager.createNamedQuery("findAll", Country.class).getResultList();
    }

    @GET
    @Path("{name}")
    public Country find(@PathParam("name") String name) {
        return entityManager.createNamedQuery("byName", Country.class).setParameter("name", name).getSingleResult();
    }
}
