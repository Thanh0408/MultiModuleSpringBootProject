package demo.com.vn.authen.dto.login.request;

import demo.com.vn.authen.util.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role; // OWNER, TENANT, ADMIN
}
