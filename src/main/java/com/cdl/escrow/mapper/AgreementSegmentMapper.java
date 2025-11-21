package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementSegmentDTO;
import com.cdl.escrow.entity.AgreementSegment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementSegmentMapper extends EntityMapper<AgreementSegmentDTO, AgreementSegment> {

    AgreementSegmentDTO toDto(AgreementSegment entity);

    AgreementSegment toEntity(AgreementSegmentDTO dto);

}
