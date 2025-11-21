package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.entity.StandingInstruction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StandingInstructionMapper extends EntityMapper<StandingInstructionDTO, StandingInstruction> {

    StandingInstructionDTO toDto(StandingInstruction entity);

    StandingInstruction toEntity(StandingInstructionDTO dto);

}