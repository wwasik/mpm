package wwasik.mpm.controller;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyRepository repository;

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<Company> findAll() {
        return repository.findAll();
    }
    
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public void save(@Valid @RequestBody(required = true) Company company) {
        repository.save(company);
    }
    
    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody(required = true) Company company) {
        Company currentCompany = repository.findOne(company.getId());
        if (currentCompany != null) {
            company.setId(currentCompany.getId());
            repository.save(company);
        }
    }
    
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public Company findOne(@PathVariable Serializable id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Serializable id) {
        repository.delete(id);
    }
}
