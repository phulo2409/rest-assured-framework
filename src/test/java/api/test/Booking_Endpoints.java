package api.test;

import api.endpoints.BookingEndPoints;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.Booking;
import payload.BookingAuth;

public class Booking_Endpoints {
    Faker faker;
    Booking bookingPayload;
    BookingAuth bookingAuthPayload;
    int bookingid;
    String token;

    @BeforeClass
    public void beforeClass(){
        faker = new Faker();
        bookingPayload = new Booking();
        bookingAuthPayload = new BookingAuth();

        bookingPayload.setFirstname(faker.name().firstName());
        bookingPayload.setLastname(faker.name().lastName());
        bookingPayload.setTotalprice(faker.number().numberBetween(100, 999));
        bookingPayload.setDepositpaid(true);
        bookingPayload.setBookingDates("2018-01-01", "2019-01-01");
        bookingPayload.setAdditionalneeds(faker.food().dish());

        bookingAuthPayload.setUsername("admin");
        bookingAuthPayload.setPassword("password123");

        token = BookingEndPoints.loginBooking(bookingAuthPayload);
        System.out.println(token);
    }

    @Test(priority = 1)
    public void CreateBooking(){
        Response response = BookingEndPoints.createBooking(bookingPayload);
        response.then().log().all();
        bookingid = response.jsonPath().getInt("bookingid");
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(bookingid);
    }

    @Test(priority = 2)
    public void UpdateBooking(){
        bookingPayload.setFirstname(faker.name().firstName());
        bookingPayload.setLastname(faker.name().lastName());
        bookingPayload.setTotalprice(faker.number().numberBetween(100, 999));
        bookingPayload.setDepositpaid(true);
        Response response = BookingEndPoints.updateBooking(bookingPayload, token, bookingid);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void GetDetailBooking(){
        Response response = BookingEndPoints.getBookingDetail(bookingid);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void DeleteBooking(){
        Response response = BookingEndPoints.deteleBooking(token, bookingid);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
    }

}
