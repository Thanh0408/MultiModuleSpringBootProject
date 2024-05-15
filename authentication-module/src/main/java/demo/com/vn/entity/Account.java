package demo.com.vn.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ACCOUNT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "ACCESS_TOKEN")
    String accessToken;

    @Column(name = "REFRESH_TOKEN")
    String refreshToken;

    @ManyToMany
    @JoinTable(
            name = "ACCOUNT_ROLE",
            foreignKey = @ForeignKey(name = "FK_ACCOUNT_ROLE"),
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_NAME")
    )
    Set<Role> roles;

}