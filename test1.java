import org.json.JSONObject;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

//.filter(new AllureRestAssured())
@Epic("REST API Regression Testing using TestNG")
@Feature("Verify CRUID Operations on Employee module")
public class test1 {
 
    String BaseURL = "http://reqres.in/api";
 
    @Test(description = "GET Request Operation")
    @Story("GET Request with Valid User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the details of employee of id-2")
    public void verifyUser() {
 
        // GIVEN
        RestAssured.given()
               .filter(new AllureRestAssured())
 
        // WHEN
        .when()
               .get(BaseURL + "/users")
                 
        // THEN
          .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
                 
                 // To verify booking id at index 2
                //.body("data.employee_name", equalTo("Garrett Winters"))
               // .body("message", equalTo("Successfully! Record has been fetched."));
    }
 
    @Test(description = "GET Request Operation")
    @Story("GET Request with Invalid User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the details of employee of id-99999")
    public void verifyInvalidUser() {
 
        // Given
        RestAssured.given()
              .filter(new AllureRestAssured())
 
        // WHEN
        .when()
              .get(BaseURL + "/v1/employee/99999")
                 
        // THEN
        .then()
               .statusCode(200)
               .statusLine("HTTP/1.1 200 OK")
               .body("message", equalTo("Successfully! Record has not been fetched."));
    }
 
    @Test(description = "POST Request Operation")
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the creation of a new employee")
    public void createUser() {
 
        JSONObject data = new JSONObject();
 
        data.put("employee_name", "APITest");
        data.put("employee_salary", "99999");
        data.put("employee_age", "30");
 
        //GIVEN 
        RestAssured.given()
              .filter(new AllureRestAssured())
              .contentType(ContentType.JSON)
              .body(data.toString())
 
        // WHEN
        .when()
               .post(BaseURL + "/v1/create")
 
        // THEN
        .then()
               .statusCode(200)
               .body("data.employee_name", equalTo("APITest"))
               .body("message", equalTo("Successfully! Record has been added."));
 
    }
 
}