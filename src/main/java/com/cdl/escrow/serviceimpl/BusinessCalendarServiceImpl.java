package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BusinessCalendarDTO;
import com.cdl.escrow.entity.BusinessCalendar;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BusinessCalendarMapper;
import com.cdl.escrow.repository.BusinessCalendarRepository;
import com.cdl.escrow.service.BusinessCalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessCalendarServiceImpl implements BusinessCalendarService {

    private final BusinessCalendarRepository repository;

    private final BusinessCalendarMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BusinessCalendarDTO> getAllBusinessCalendar(Pageable pageable) {
        log.debug("Fetching all business calender, page: {}", pageable.getPageNumber());
        Page<BusinessCalendar> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessCalendarDTO> getBusinessCalendarById(Long id) {
        log.debug("Fetching business calender with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public BusinessCalendarDTO saveBusinessCalendar(BusinessCalendarDTO businessCalendarDTO) {
        log.info("Saving new business calender");
        BusinessCalendar entity = mapper.toEntity(businessCalendarDTO);
        BusinessCalendar saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public BusinessCalendarDTO updateBusinessCalendar(Long id, BusinessCalendarDTO businessCalendarDTO) {
        log.info("Updating business calender with ID: {}", id);

        BusinessCalendar existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("business calender not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BusinessCalendar toUpdate = mapper.toEntity(businessCalendarDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        BusinessCalendar updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteBusinessCalendarById(Long id) {
        log.info("Deleting business calender with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("business calender not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteBusinessCalendarById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
