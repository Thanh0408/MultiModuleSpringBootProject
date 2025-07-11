package demo.com.vn.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumStatus {
    ACTIVE("ACTIVE", "Hoạt động"),
    INACTIVE("INACTIVE", "Vô hiệu hóa");
    private final String code;
    private final String description;


    public static EnumStatus fromCode(String code) {
        for (EnumStatus status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid RentalStatus code: " + code);
    }
}
