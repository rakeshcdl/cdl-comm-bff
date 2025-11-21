package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ScheduledJobDTO;
import com.cdl.escrow.entity.ScheduledJob;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduledJobMapper extends EntityMapper<ScheduledJobDTO, ScheduledJob> {

    ScheduledJobDTO toDto(ScheduledJob entity);

    ScheduledJob toEntity(ScheduledJobDTO dto);

}