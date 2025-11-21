package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.CountryDTO;
import com.cdl.escrow.entity.Country;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    CountryDTO toDto(Country entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    Country toEntity(CountryDTO dto);

}
