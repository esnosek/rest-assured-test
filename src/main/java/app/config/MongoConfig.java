package app.config;

import app.model.Book;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "app.repository")
public class MongoConfig extends AbstractMongoConfiguration implements InitializingBean{

    @Override
    public String getDatabaseName() {
        return "library";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "library");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        mongoTemplate().remove(new Query(), "books");
        Book book1 = new Book("1001", "Ogniem i Mieczem", "Sienkiewicz", 1880);
        Book book2 = new Book("1002","W Pustyni i w puszczy", "Sienkiewicz", 1870);
        Book book3 = new Book("1003","Zbrodnia i Kara", "Dostojewski", 1888);
        Book book4 = new Book("1004","Bracia Karamazow", "Dostojewski", 1875);
        Book book5 = new Book("1005","Wprowadzenie do algorytmów", "Cormen", 1980);
        mongoTemplate().save(book1);
        mongoTemplate().save(book2);
        mongoTemplate().save(book3);
        mongoTemplate().save(book4);
        mongoTemplate().save(book5);
    }
}
