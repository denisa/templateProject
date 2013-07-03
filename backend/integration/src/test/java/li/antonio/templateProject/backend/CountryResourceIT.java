package li.antonio.templateProject.backend;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class CountryResourceIT {

    private WebTarget root;

    @Before
    public void createClient() {
        final Client client = ClientBuilder.newClient();
        root = client.target("http://localhost:8080/endpoints/api/countries");
    }

    @Test
    public void getACountry() {
        {
            final Response response = root.path("Suisse").request().get();
            assertThat(response.getStatus(), is(200));
            final Country country = response.readEntity(Country.class);
            assertThat(country.getName(), is("Suisse"));
        }
        {
            final Response response = root.path("France").request().get();
            assertThat(response.getStatus(), is(200));
            final Country country = response.readEntity(Country.class);
            assertThat(country.getName(), is("France"));
        }
    }

    @Test
    public void getAllCountries() {
        final Response response = root.request().get();
        assertThat(response.getStatus(), is(200));
        final List<Country> countries = response.readEntity(new GenericType<List<Country>>(){});
        assertThat(countries.size(), is(2));
    }
}
