package common;

import com.github.javafaker.Faker;

public class testDataGenerator {

    public static String userName = Faker.instance().name().fullName();
    public static String userjobName = Faker.instance().job().position();
    public static String userCompanyName = Faker.instance().company().name();

}
