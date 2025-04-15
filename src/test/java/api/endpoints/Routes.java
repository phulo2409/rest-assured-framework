package api.endpoints;

/*

Swagger URI -> https://petstore.swagger.io/
Create User (Post): https://petstore.swagger.io/v2/user
Get User (Get): https://petstore.swagger.io/v2/user{username}
Update User (Put): https://petstore.swagger.io/v2/user/{username}
Delete User (Delete): https://petstore.swagger.io/v2/user/{username}

 */

public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";

    // User Module
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";

    // Store Module

        // Here you will create Store module URL's

    // Pet Module

        // Here  you will create Pet module URL's

    // Booking Module
    public static String base_booking_url = "https://restful-booker.herokuapp.com";

    public static String post_booking_url = base_booking_url + "/booking";
    public static String get_booking_detail_url = base_booking_url + "/booking/{bookingid}";
    public static String update_booking_url = base_booking_url + "/booking/{bookingid}";
    public static String delete_booking_url = base_booking_url + "/booking/{bookingid}";
    public static String login_booking_url = base_booking_url + "/auth";





}
