package demo.com.vn.mapping;

import demo.com.vn.dto.BuildingDTO;
import demo.com.vn.entity.BuildingEntity;
import demo.com.vn.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BuildingMapper extends BaseMapper<BuildingDTO, BuildingEntity> {

    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);
}
