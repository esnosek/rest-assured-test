package app.service;

import app.model.Facebook;
import app.repository.FacebookRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FacebookService {

    @Autowired
    private FacebookRepository facebookRepository;

    public Flux<Facebook> create(Publisher<Facebook> facebook) {
        return facebookRepository.insert(facebook);
    }

    public Mono<Facebook> find(String id){
        return facebookRepository.findById(id);
    }

    public Flux<Facebook> findAll() {
        return facebookRepository.findAll();
    }

    public Mono<Void> delete(String id){
        return facebookRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return facebookRepository.deleteAll();
    }

}
