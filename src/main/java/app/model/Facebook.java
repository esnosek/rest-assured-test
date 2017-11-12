package app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facebooks")
public class Facebook {

    @Id
    private String id;
    private String name;
    private int age;

    public Facebook(){
    }

    public Facebook(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Facebook [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
