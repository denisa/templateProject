package li.antonio.templateProject.endpoints;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("countries")
public class CountryResource {

    @GET
    public List<Country> getAll() {
        return Collections.emptyList();
    }

    @GET
    @Path("{name}")
    public Country find(@PathParam("name") String name) {
        return new Country(name);
    }
}
