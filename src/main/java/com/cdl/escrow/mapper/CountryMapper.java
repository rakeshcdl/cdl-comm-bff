package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.CountryDTO;
import com.cdl.escrow.entity.Country;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    CountryDTO toDto(Country entity);

    Country toEntity(CountryDTO dto);

}
