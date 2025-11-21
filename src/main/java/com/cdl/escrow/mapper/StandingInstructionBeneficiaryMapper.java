package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import com.cdl.escrow.entity.StandingInstructionBeneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StandingInstructionBeneficiaryMapper extends EntityMapper<StandingInstructionBeneficiaryDTO, StandingInstructionBeneficiary> {

    @Mapping(source = "beneficiaryName", target = "beneficiaryNameDTO")
    @Mapping(source = "paymentMode", target = "paymentModeDTO")
    @Mapping(source = "currency", target = "currencyDTO")
    @Mapping(source = "standingInstruction", target = "standingInstructionDTO")
    StandingInstructionBeneficiaryDTO toDto(StandingInstructionBeneficiary entity);

    @Mapping(source = "beneficiaryNameDTO", target = "beneficiaryName")
    @Mapping(source = "paymentModeDTO", target = "paymentMode")
    @Mapping(source = "currencyDTO", target = "currency")
    @Mapping(source = "standingInstructionDTO", target = "standingInstruction")
    StandingInstructionBeneficiary toEntity(StandingInstructionBeneficiaryDTO dto);

}
