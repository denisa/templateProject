package li.antonio.templateProject.backend;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class CountryResourceIT {
    @Test
    public void getACountry() {
        final Client client = ClientBuilder.newClient();
        final WebTarget root = client.target("http://localhost:8080/endpoints/api/countries");

        final Response response = root.path("Suisse").request().get();
        assertThat(response.getStatus(), is(200));
        final Country country = response.readEntity(Country.class);
        assertThat(country.getName(), is("Suisse"));
    }
}
