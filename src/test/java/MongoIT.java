import app.config.MongoConfig;
import app.config.WebFluxConfig;
import app.controller.FacebookController;
import app.dto.FacebookDto;
import app.service.FacebookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Mono;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebFluxConfig.class, MongoConfig.class, FacebookService.class})
public class MongoIT {

    @Autowired
    private FacebookController facebookController;

    @Test
    public void testGetFacebookAccount() {
        facebookController.create(Mono.just(new FacebookDto("namsse", 13)));
    }

}
