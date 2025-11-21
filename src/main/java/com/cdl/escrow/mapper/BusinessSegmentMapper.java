package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BusinessSegmentDTO;
import com.cdl.escrow.entity.BusinessSegment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessSegmentMapper extends EntityMapper<BusinessSegmentDTO, BusinessSegment> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    BusinessSegmentDTO toDto(BusinessSegment entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    BusinessSegment toEntity(BusinessSegmentDTO dto);

}

