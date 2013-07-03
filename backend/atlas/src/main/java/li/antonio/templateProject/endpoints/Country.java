package li.antonio.templateProject.endpoints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries(
        {
                @NamedQuery(name = "findAll", query = "SELECT DISTINCT c FROM Country c"),
                @NamedQuery(name = "byName", query = "SELECT DISTINCT c FROM Country c WHERE c.name = :name")
        }
)
@Entity
public class Country {
    @Id
    @GeneratedValue
    private Integer id;
    @Version
    private Integer version;
    @NotNull
    private String name;

    public Country(final String name) {
        this.name = name;
    }

    protected Country() {
    }

    public String getName() {
        return name;
    }
}
