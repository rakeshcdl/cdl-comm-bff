package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BusinessSegmentDTO;
import com.cdl.escrow.entity.BusinessSegment;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BusinessSegmentMapper;
import com.cdl.escrow.repository.BusinessSegmentRepository;
import com.cdl.escrow.service.BusinessSegmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessSegmentServiceImpl implements BusinessSegmentService {

    private final BusinessSegmentRepository repository;

    private final BusinessSegmentMapper mapper;


    @Override
    public Page<BusinessSegmentDTO> getAllBusinessSegment(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<BusinessSegment> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BusinessSegmentDTO> getBusinessSegmentById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BusinessSegmentDTO saveBusinessSegment(BusinessSegmentDTO businessSegmentDTO) {
        log.info("Saving new application language code");
        BusinessSegment entity = mapper.toEntity(businessSegmentDTO);
        BusinessSegment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BusinessSegmentDTO updateBusinessSegment(Long id, BusinessSegmentDTO businessSegmentDTO) {
        log.info("Updating application language code with ID: {}", id);

        BusinessSegment existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BusinessSegment toUpdate = mapper.toEntity(businessSegmentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        BusinessSegment updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBusinessSegmentById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteBusinessSegmentById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
