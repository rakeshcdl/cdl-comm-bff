package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import com.cdl.escrow.entity.BusinessSubSegment;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BusinessSubSegmentMapper;
import com.cdl.escrow.repository.BusinessSubSegmentRepository;
import com.cdl.escrow.service.BusinessSubSegmentService;
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
public class BusinessSubSegmentServiceImpl implements BusinessSubSegmentService {

    private final BusinessSubSegmentRepository repository;

    private final BusinessSubSegmentMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<BusinessSubSegmentDTO> getAllBusinessSubSegment(Pageable pageable) {
        log.debug("Fetching all business sub segment, page: {}", pageable.getPageNumber());
        Page<BusinessSubSegment> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessSubSegmentDTO> getBusinessSubSegmentById(Long id) {
        log.debug("Fetching business sub segment with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public BusinessSubSegmentDTO saveBusinessSubSegment(BusinessSubSegmentDTO businessSubSegmentDTO) {
        log.info("Saving new business sub segment");
        BusinessSubSegment entity = mapper.toEntity(businessSubSegmentDTO);
        BusinessSubSegment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public BusinessSubSegmentDTO updateBusinessSubSegment(Long id, BusinessSubSegmentDTO businessSubSegmentDTO) {
        log.info("Updating business sub segment with ID: {}", id);

        BusinessSubSegment existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("business sub segment not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BusinessSubSegment toUpdate = mapper.toEntity(businessSubSegmentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        BusinessSubSegment updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteBusinessSubSegmentById(Long id) {
        log.info("Deleting business sub segment with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("business sub segment not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteBusinessSubSegmentById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
