package app.controller;

import app.dto.FacebookDto;
import app.model.Facebook;
import app.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/facebook")
public class FacebookController {

    @Autowired
    private FacebookService facebookService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Facebook> create(@RequestBody Flux<FacebookDto> facebookDto){
        Flux<Facebook> facebook = facebookDto.map(f -> new Facebook(f.getName(), f.getAge()));
        return facebookService.create(facebook);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Facebook> find(@PathVariable String id){
        Mono<Facebook> facebook = facebookService.find(id);
        return facebook;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Facebook> findAll(){
        Flux<Facebook> facebooks = facebookService.findAll();
        return facebooks;
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> delete(@PathVariable String id){
         return facebookService.delete(id);
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> deleteAll(){
        return facebookService.deleteAll();
    }

}
