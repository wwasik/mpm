package wwasik.mpm.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import wwasik.mpm.model.Company;

/**
 * @author Wojtek
 */
public interface CompanyRepository extends MongoRepository<Company, Serializable> {

}
