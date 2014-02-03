package wwasik.mpm.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

/**
 * @author Wojtek
 */
public class Company {

    @Id
    private String id;
    @NotEmpty
    private String name;
    private String description;
    private String website;
    private String logo;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }    
}