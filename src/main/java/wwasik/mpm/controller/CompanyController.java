package wwasik.mpm.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public void create(@Valid @RequestBody(required = true) Company company) {
        repository.save(company);
    }
}
