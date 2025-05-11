package pojoClasses;
public class CreateUsers {

    public String name;
    public String job;
    public String id;
    public String createdAt;

    public CreateUsers(String name, String job, String createdAt){
        this.name = name;
        this.job = job;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }


}
