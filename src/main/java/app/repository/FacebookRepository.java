package app.repository;

import app.model.Facebook;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FacebookRepository extends ReactiveMongoRepository<Facebook, String> {
}
