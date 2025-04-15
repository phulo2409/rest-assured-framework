package payload;

import lombok.Data;

@Data
public class BookingAuth {
    private String username;
    private String password;
}
