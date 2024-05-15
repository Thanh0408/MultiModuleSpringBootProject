package demo.com.vn.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLE")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractAuditEntity {
    @Id
    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;


    @ManyToMany(mappedBy = "roles")
    List<Account> account;

}
