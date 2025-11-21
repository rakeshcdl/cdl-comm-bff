package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.CurrencyDTO;
import com.cdl.escrow.entity.Currency;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {

    CurrencyDTO toDto(Currency entity);

    Currency toEntity(CurrencyDTO dto);

}