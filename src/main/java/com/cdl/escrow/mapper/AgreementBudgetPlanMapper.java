package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementBudgetPlanDTO;
import com.cdl.escrow.entity.AgreementBudgetPlan;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementBudgetPlanMapper extends EntityMapper<AgreementBudgetPlanDTO, AgreementBudgetPlan> {

    AgreementBudgetPlanDTO toDto(AgreementBudgetPlan entity);

    AgreementBudgetPlan toEntity(AgreementBudgetPlanDTO dto);

}

