package wwasik.mpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import wwasik.mpm.repository.ProjectRepository;

/**
 * @author Wojtek
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository repository;
    
    
}
