package wwasik.mpm.controller;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wwasik.mpm.model.Company;
import wwasik.mpm.repository.CompanyRepository;

/**
 * @author Wojtek
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> findAll() {
        return repository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@Valid @RequestBody(required = true) Company company) {
        repository.save(company);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody(required = true) Company company) {
        Company currentCompany = repository.findOne(company.getId());
        if (currentCompany != null) {
            company.setId(currentCompany.getId());
            repository.save(company);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company findOne(@PathVariable Serializable id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Serializable id) {
        repository.delete(id);
    }
}
