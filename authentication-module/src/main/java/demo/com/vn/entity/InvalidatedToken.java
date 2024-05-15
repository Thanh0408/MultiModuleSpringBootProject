package demo.com.vn.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "INVALIDATED_TOKEN")
public class InvalidatedToken {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "EXPIRY_TIME")
    Date expiryTime;
}
