package demo.com.vn.entity;


import demo.com.vn.utils.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ROOM")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "BUILDING_ID", nullable = false)
    private BuildingEntity buildingEntity;

    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    @Column(name = "FLOOR_NUMBER")
    private Integer floorNumber;

    @Column(name = "ROOM_TYPE")
    private String roomType;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "STATUS")
    private EnumStatus status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL)
    private List<RentalEntity> rentalEntities;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = EnumStatus.ACTIVE;
        }
    }
}

