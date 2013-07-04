package li.antonio.templateProject.backend.restClientTest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
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
