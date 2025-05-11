package tests.regRes;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClasses.CreateUsers;
import requestBodyBuilder.RegResRequestBuilder;

import static common.Authorisations.regResApi_Key;
import static common.testDataGenerator.userName;
import static common.testDataGenerator.userjobName;
import static io.restassured.RestAssured.given;

public class CreateUserTest {

    public String userId;

    @Test
    public void postCreatTest() {

        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when().contentType("application/json")
                .header("x-api-key", regResApi_Key)
                .body(RegResRequestBuilder.buildUsers())
                .log().all()
                .post("/api/users")
                .then()
                .log().all()
                .extract().response();

        CreateUsers createUsers = response.as(CreateUsers.class);
        userId = createUsers.getId();

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals(createUsers.getName(), userName);
        Assert.assertEquals(createUsers.getJob(), userjobName);
    }

    @Test(dependsOnMethods = "postCreatTest()")
    public void putUpdateTest() {
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when().contentType("application/json")
                .header("x-api-key", regResApi_Key)
                .body(RegResRequestBuilder.updateUser())
                .log().all()
                .put("/api/users/"+userId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(200,response.statusCode());
        System.out.println("New userId : " + userId);
    }

    @Test(dependsOnMethods = "putUpdateTest")
    public void patchUpdateTest(){
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when().contentType("application/json")
                .header("x-api-key",regResApi_Key)
                .body(RegResRequestBuilder.modifyUser())
                .log().all()
                .patch("/api/users/"+userId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(200,response.statusCode());
    }

    @Test(dependsOnMethods = "patchUpdateTest")
    public void getUserDetailsTest(){
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when().contentType("application/json")
                .header("x-api-key",regResApi_Key)
                .log().all()
                .get("/api/users/2")
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(dependsOnMethods = "getUserDetailsTest")
    public void deleteUserRecordTest(){
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when().contentType("application/json")
                .header("x-api-key",regResApi_Key)
                .log().all()
                .delete("/api/users/"+userId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(),204);
    }
}
