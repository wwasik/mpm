package wwasik.mpm.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import wwasik.mpm.model.Employee;

/**
 * @author Wojtek
 */
public interface EmployeeRepository extends MongoRepository<Employee, Serializable>{

}
