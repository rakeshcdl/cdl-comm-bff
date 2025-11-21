package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ScheduledJobDTO;
import com.cdl.escrow.entity.ScheduledJob;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduledJobMapper extends EntityMapper<ScheduledJobDTO, ScheduledJob> {

    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "standingInstruction", target = "standingInstructionDTO")
    ScheduledJobDTO toDto(ScheduledJob entity);

    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "standingInstructionDTO", target = "standingInstruction")
    ScheduledJob toEntity(ScheduledJobDTO dto);

}