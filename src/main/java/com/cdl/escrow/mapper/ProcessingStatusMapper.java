package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ProcessingStatusDTO;
import com.cdl.escrow.entity.ProcessingStatus;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessingStatusMapper  extends EntityMapper<ProcessingStatusDTO, ProcessingStatus> {

    ProcessingStatusDTO toDto(ProcessingStatus entity);

    ProcessingStatus toEntity(ProcessingStatusDTO dto);

}
