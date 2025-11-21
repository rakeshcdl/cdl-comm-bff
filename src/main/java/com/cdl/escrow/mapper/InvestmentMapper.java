package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.InvestmentDTO;
import com.cdl.escrow.entity.Investment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvestmentMapper extends EntityMapper<InvestmentDTO, Investment> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    InvestmentDTO toDto(Investment entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    Investment toEntity(InvestmentDTO dto);

}