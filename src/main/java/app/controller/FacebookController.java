package app.controller;

import app.dto.FacebookDto;
import app.model.Facebook;
import app.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facebook")
public class FacebookController {

    @Autowired
    private FacebookService facebookService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facebook> create(@RequestBody FacebookDto facebookDto){
        Facebook facebook = facebookService.create(new Facebook(facebookDto.getName(), facebookDto.getAge()));
        return new ResponseEntity<>(facebook, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facebook> find(@PathVariable String id){
        Facebook facebook = facebookService.find(id);
        return facebook == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(facebook, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Facebook>> findAll(){
        List<Facebook> facebooks = facebookService.findAll();
        return new ResponseEntity<>(facebooks, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facebook> delete(@PathVariable String id){
        facebookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facebook> deleteAll(){
        facebookService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
