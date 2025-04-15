package api.test;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Booking {
    int id;
    String token;

    @Test(priority = 1)
    public void CreateBooking() {
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        JSONObject data = new JSONObject();
        data.put("firstname", "Phu");
        data.put("lastname", "Lo");
        data.put("totalprice", 111);
        data.put("depositpaid", true);
        data.put("bookingdates", bookingDates);
        data.put("additionalneeds", "Breakfast");

        id = given()
                .contentType("application/json")
                .accept("application/json")
                .body(data.toString())

            .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .jsonPath().getInt("bookingid");
    }

    @Test(priority = 2)
    public void Login(){
        JSONObject account = new JSONObject();
        account.put("username", "admin");
        account.put("password", "password123");

        token = given()
                .contentType("application/json")
                .body(account.toString())
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .jsonPath().getString("token");
    }

    @Test(priority = 3)
    public void UpdateBooking(){
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        JSONObject data = new JSONObject();
        data.put("firstname", "Hung");
        data.put("lastname", "Nguyen");
        data.put("totalprice", 456);
        data.put("depositpaid", true);
        data.put("bookingdates", bookingDates);
        data.put("additionalneeds", "Breakfast");

        given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token="+token)
                .header("Authorisation", "Basic " + token)
                .pathParam("id", id)
                .body(data.toString())

            .when()
                .put("https://restful-booker.herokuapp.com/booking/{id}")
            .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void GetDetailBooking(){

        given()
                .accept("application/json")
                .pathParam("id", id)
            .when()
                .get("https://restful-booker.herokuapp.com/booking/{id}")
            .then()
                .statusCode(200)
                .body("firstname", equalTo("Hung"))
                .body("lastname", equalTo("Nguyen"))
                .body("totalprice", equalTo(456))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .log().all();
    }

    @Test(priority = 5)
    public void DeteleBooking(){

        given()
                .header("Cookie", "token="+token)
                .header("Authorisation", "Basic " + token)
                .pathParam("id", id)
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/{id}")
                .then()
                .statusCode(201)
                .log().all();
    }
}
