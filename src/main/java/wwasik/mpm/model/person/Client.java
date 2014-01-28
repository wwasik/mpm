package wwasik.mpm.model.person;

import wwasik.mpm.model.Company;

/**
 * @author dom
 */
public class Client extends Person {
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
