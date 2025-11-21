package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BusinessCalendarDTO;
import com.cdl.escrow.entity.BusinessCalendar;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessCalendarMapper extends EntityMapper<BusinessCalendarDTO, BusinessCalendar> {

    BusinessCalendarDTO toDto(BusinessCalendar entity);

    BusinessCalendar toEntity(BusinessCalendarDTO dto);

}
