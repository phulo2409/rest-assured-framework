package api.endpoints;

import io.restassured.response.Response;
import payload.Booking;
import payload.BookingAuth;

import static io.restassured.RestAssured.given;

public class BookingEndPoints {

    public static Response createBooking(Booking payload){
       Response response = given()
               .contentType("application/json")
               .accept("application/json")
               .body(payload)
           .when()
               .post(Routes.post_booking_url);
        return response;
    }

    public static Response updateBooking(Booking payload, String token, int bookingid){
        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token="+token)
                .header("Authorisation", "Basic " + token)
                .pathParam("bookingid", bookingid)
                .body(payload)
            .when()
                .put(Routes.update_booking_url);
        return  response;
    }

    public static Response getBookingDetail(int bookingid){
        Response response = given()
                .accept("application/json")
                .pathParam("bookingid", bookingid)
            .when()
                .get(Routes.get_booking_detail_url);
        return response;
    }

    public static Response deteleBooking(String token, int bookingid){
        Response response = given()
                .header("Cookie", "token="+token)
                .header("Authorisation", "Basic " + token)
                .pathParam("bookingid", bookingid)
            .when()
                .delete(Routes.delete_booking_url);
        return response;
    }

    public static String loginBooking(BookingAuth payload){
        Response response = given()
                .contentType("application/json")
                .body(payload)
            .when()
                .post(Routes.login_booking_url);
        return response.jsonPath().getString("token");
    }


}
