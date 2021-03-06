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
import wwasik.mpm.model.Project;
import wwasik.mpm.repository.ProjectRepository;

/**
 * @author Wojtek
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> findAll() {
        return repository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@Valid @RequestBody(required = true) Project p) {
        repository.save(p);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody(required = true) Project p) {
        Project currentProject = repository.findOne(p.getId());
        if (currentProject != null) {
            p.setId(currentProject.getId());
            repository.save(p);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findOne(@PathVariable Serializable id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Serializable id) {
        repository.delete(id);
    }
}
