package demo.com.vn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumIsDelete {
    ACTIVE(0, "Active"),
    IN_ACTIVE(1, "In-active"),
    ;
    private final int code;
    private final String descr;
}
