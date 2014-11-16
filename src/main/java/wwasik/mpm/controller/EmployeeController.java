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
import wwasik.mpm.model.Employee;
import wwasik.mpm.repository.EmployeeRepository;

/**
 * @author Wojtek
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll() {
        return repository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@Valid @RequestBody(required = true) Employee e) {
        repository.save(e);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody(required = true) Employee e) {
        Employee current = repository.findOne(e.getId());
        if (current != null) {
            current.setMail(e.getMail());
            current.setName(e.getName());
            current.setPhone(e.getPhone());
            current.setRank(e.getRank());
            current.setSurname(e.getSurname());
            repository.save(e);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee findOne(@PathVariable Serializable id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Serializable id) {
        repository.delete(id);
    }
}
