package com.cdl.escrow.service;

import com.cdl.escrow.dto.BusinessCalendarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BusinessCalendarService {

    Page<BusinessCalendarDTO> getAllBusinessCalendar(final Pageable pageable);

    Optional<BusinessCalendarDTO> getBusinessCalendarById(Long id);

    BusinessCalendarDTO saveBusinessCalendar(BusinessCalendarDTO businessCalendarDTO);

    BusinessCalendarDTO updateBusinessCalendar(Long id, BusinessCalendarDTO businessCalendarDTO);

    Boolean deleteBusinessCalendarById(Long id);

    boolean softDeleteBusinessCalendarById(Long id);
}
