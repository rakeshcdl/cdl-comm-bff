package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.CurrencyDTO;
import com.cdl.escrow.entity.Currency;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    CurrencyDTO toDto(Currency entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    Currency toEntity(CurrencyDTO dto);

}