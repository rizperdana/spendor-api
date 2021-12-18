package spendor.core.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import spendor.core.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  List<User> findByUsernameContaining(String username);
  List<User> findByEmailContaining(String email);
  List<User> findByPhoneContaining(String phone);
  List<User> findByActive(boolean active);
}
