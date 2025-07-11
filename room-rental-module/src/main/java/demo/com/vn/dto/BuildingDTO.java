package demo.com.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDTO {

    private Long buildingId;

    private String name;

    private String address;

    private Integer numFloors;
}
