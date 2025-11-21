package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import com.cdl.escrow.entity.BusinessSubSegment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessSubSegmentMapper extends EntityMapper<BusinessSubSegmentDTO, BusinessSubSegment> {

    @Mapping(source = "businessSegmentName", target = "businessSegmentNameDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    BusinessSubSegmentDTO toDto(BusinessSubSegment entity);


    @Mapping(source = "businessSegmentNameDTO", target = "businessSegmentName")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    BusinessSubSegment toEntity(BusinessSubSegmentDTO dto);

}

