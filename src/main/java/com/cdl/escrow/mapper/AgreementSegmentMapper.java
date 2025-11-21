package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementSegmentDTO;
import com.cdl.escrow.entity.AgreementSegment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementSegmentMapper extends EntityMapper<AgreementSegmentDTO, AgreementSegment> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AgreementSegmentDTO toDto(AgreementSegment entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AgreementSegment toEntity(AgreementSegmentDTO dto);

}
