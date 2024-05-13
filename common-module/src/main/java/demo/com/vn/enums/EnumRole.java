package demo.com.vn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumRole {
    ADMIN("ADMIN", "Admin của hệ thống"),
    USER("USER", "User (người dùng) của hệ thống")
    ;
    private final String code;
    private final String descr;
}
