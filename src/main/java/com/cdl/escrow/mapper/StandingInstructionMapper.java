package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.entity.StandingInstruction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StandingInstructionMapper extends EntityMapper<StandingInstructionDTO, StandingInstruction> {

    @Mapping(source = "dealNo", target = "dealNoDTO")
    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "transferType", target = "transferTypeDTO")
    @Mapping(source = "occurrence", target = "occurrenceDTO")
    @Mapping(source = "recurringFrequency", target = "recurringFrequencyDTO")
    @Mapping(source = "holidaySetup", target = "holidaySetupDTO")
    @Mapping(source = "dependentScenario", target = "dependentScenarioDTO")
    @Mapping(source = "formAccountDr", target = "formAccountDrDTO")
    @Mapping(source = "dependence", target = "dependenceDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    StandingInstructionDTO toDto(StandingInstruction entity);

    @Mapping(source = "dealNoDTO", target = "dealNo")
    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "transferTypeDTO", target = "transferType")
    @Mapping(source = "occurrenceDTO", target = "occurrence")
    @Mapping(source = "recurringFrequencyDTO", target = "recurringFrequency")
    @Mapping(source = "holidaySetupDTO", target = "holidaySetup")
    @Mapping(source = "dependentScenarioDTO", target = "dependentScenario")
    @Mapping(source = "formAccountDrDTO", target = "formAccountDr")
    @Mapping(source = "dependenceDTO", target = "dependence")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    StandingInstruction toEntity(StandingInstructionDTO dto);

}