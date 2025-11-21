package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import com.cdl.escrow.entity.BusinessSubSegment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessSubSegmentMapper extends EntityMapper<BusinessSubSegmentDTO, BusinessSubSegment> {

    BusinessSubSegmentDTO toDto(BusinessSubSegment entity);

    BusinessSubSegment toEntity(BusinessSubSegmentDTO dto);

}

