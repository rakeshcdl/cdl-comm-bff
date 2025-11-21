package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementBudgetPlanDTO;
import com.cdl.escrow.entity.AgreementBudgetPlan;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementBudgetPlanMapper extends EntityMapper<AgreementBudgetPlanDTO, AgreementBudgetPlan> {

    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    AgreementBudgetPlanDTO toDto(AgreementBudgetPlan entity);

    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    AgreementBudgetPlan toEntity(AgreementBudgetPlanDTO dto);

}

