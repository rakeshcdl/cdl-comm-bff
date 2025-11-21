package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BusinessCalendarDTO;
import com.cdl.escrow.entity.BusinessCalendar;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessCalendarMapper extends EntityMapper<BusinessCalendarDTO, BusinessCalendar> {

    @Mapping(source = "deductionToHappen", target = "deductionToHappenDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    BusinessCalendarDTO toDto(BusinessCalendar entity);


    @Mapping(source = "deductionToHappenDTO", target = "deductionToHappen")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    BusinessCalendar toEntity(BusinessCalendarDTO dto);

}
