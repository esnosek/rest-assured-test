package app.service;

import app.model.Facebook;
import app.repository.FacebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacebookService {

    @Autowired
    private FacebookRepository facebookRepository;

    public Facebook create(Facebook facebook) {
        return facebookRepository.insert(facebook);
    }

    public Facebook find(String id){
        return facebookRepository.findOne(id);
    }

    public List<Facebook> findAll() {
        return facebookRepository.findAll();
    }

    public void delete(String id){
        facebookRepository.delete(id);
    }

    public void deleteAll() {
        facebookRepository.deleteAll();
    }

}
