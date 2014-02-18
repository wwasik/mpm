package wwasik.mpm.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import wwasik.mpm.model.Project;

/**
 * @author Wojtek
 */
public interface ProjectRepository extends MongoRepository<Project, Serializable>{

}
