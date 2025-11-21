package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.InvestmentDTO;
import com.cdl.escrow.entity.Investment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvestmentMapper extends EntityMapper<InvestmentDTO, Investment> {

    InvestmentDTO toDto(Investment entity);

    Investment toEntity(InvestmentDTO dto);

}