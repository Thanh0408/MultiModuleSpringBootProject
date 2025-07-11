package demo.com.vn.service;

import demo.com.vn.dto.BuildingDTO;
import demo.com.vn.entity.BuildingEntity;
import demo.com.vn.mapping.BuildingMapper;
import demo.com.vn.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingDTO save(BuildingDTO reqDTO) {
        buildingRepository.findByAddress(reqDTO.getAddress()).ifPresent(item -> {throw new IllegalStateException("Building address already taken");});

        return BuildingMapper.INSTANCE.toDto(
                buildingRepository.save(
                        BuildingMapper.INSTANCE.toEntity(reqDTO)
                )
        );
    }

    public Page<BuildingDTO> findBuildings(Pageable pageable) {
        Page<BuildingEntity> pageBuilding =buildingRepository.findAll(pageable);
        return pageBuilding.map(BuildingMapper.INSTANCE::toDto);
    }
}
