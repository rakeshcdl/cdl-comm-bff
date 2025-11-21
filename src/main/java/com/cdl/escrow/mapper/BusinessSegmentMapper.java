package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BusinessSegmentDTO;
import com.cdl.escrow.entity.BusinessSegment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessSegmentMapper extends EntityMapper<BusinessSegmentDTO, BusinessSegment> {

    BusinessSegmentDTO toDto(BusinessSegment entity);

    BusinessSegment toEntity(BusinessSegmentDTO dto);

}

