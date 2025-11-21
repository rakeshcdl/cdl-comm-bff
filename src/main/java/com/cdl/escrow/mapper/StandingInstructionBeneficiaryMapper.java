package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import com.cdl.escrow.entity.StandingInstructionBeneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StandingInstructionBeneficiaryMapper extends EntityMapper<StandingInstructionBeneficiaryDTO, StandingInstructionBeneficiary> {

    StandingInstructionBeneficiaryDTO toDto(StandingInstructionBeneficiary entity);

    StandingInstructionBeneficiary toEntity(StandingInstructionBeneficiaryDTO dto);

}
