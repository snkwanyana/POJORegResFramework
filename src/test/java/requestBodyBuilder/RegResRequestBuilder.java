package requestBodyBuilder;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

import static common.testDataGenerator.*;

public class RegResRequestBuilder {

    static String name = userName;
    static String job = userjobName;
    static String company = userCompanyName;

    public static JSONObject buildUsers(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("job",job);

        return jsonObject;
    }

    public static JSONObject updateUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("job",job);
        jsonObject.put("company",company);

        return jsonObject;

    }

    public static JSONObject modifyUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", Faker.instance().name().fullName());
        jsonObject.put("job",job);
        jsonObject.put("company",company);

        return jsonObject;
    }
}
