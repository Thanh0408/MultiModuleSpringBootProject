package demo.com.vn.authen.dto.login.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String role; // OWNER, TENANT, ADMIN
}
