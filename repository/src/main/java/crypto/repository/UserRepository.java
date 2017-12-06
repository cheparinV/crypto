package crypto.repository;

import crypto.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
