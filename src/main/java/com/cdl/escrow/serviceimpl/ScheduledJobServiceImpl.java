package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ScheduledJobDTO;
import com.cdl.escrow.entity.ScheduledJob;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ScheduledJobMapper;
import com.cdl.escrow.repository.ScheduledJobRepository;
import com.cdl.escrow.service.ScheduledJobService;
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
public class ScheduledJobServiceImpl implements ScheduledJobService {

    private final ScheduledJobRepository repository;

    private final ScheduledJobMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ScheduledJobDTO> getAllScheduledJob(Pageable pageable) {
        log.debug("Fetching all scheduled job, page: {}", pageable.getPageNumber());
        Page<ScheduledJob> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ScheduledJobDTO> getScheduledJobById(Long id) {
        log.debug("Fetching scheduled job with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ScheduledJobDTO saveScheduledJob(ScheduledJobDTO scheduledJobDTO) {
        log.info("Saving new scheduled job");
        ScheduledJob entity = mapper.toEntity(scheduledJobDTO);
        ScheduledJob saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ScheduledJobDTO updateScheduledJob(Long id, ScheduledJobDTO scheduledJobDTO) {
        log.info("Updating scheduled job with ID: {}", id);

        ScheduledJob existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("scheduled job not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ScheduledJob toUpdate = mapper.toEntity(scheduledJobDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ScheduledJob updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteScheduledJobById(Long id) {
        log.info("Deleting scheduled job with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("scheduled job not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteScheduledJobById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
