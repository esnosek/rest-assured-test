package app.repository;

import app.model.Facebook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacebookRepository extends MongoRepository<Facebook, String> {
}
