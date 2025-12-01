package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import com.cdl.escrow.entity.AgreementFeeSchedule;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementFeeScheduleMapper;
import com.cdl.escrow.repository.AgreementFeeScheduleRepository;
import com.cdl.escrow.service.AgreementFeeScheduleService;
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
public class AgreementFeeScheduleServiceImpl implements AgreementFeeScheduleService {

    private final AgreementFeeScheduleRepository repository;

    private final AgreementFeeScheduleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<AgreementFeeScheduleDTO> getAllAgreementFeeSchedule(Pageable pageable) {
        log.debug("Fetching all agreement fee, page: {}", pageable.getPageNumber());
        Page<AgreementFeeSchedule> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgreementFeeScheduleDTO> getAgreementFeeScheduleById(Long id) {
        log.debug("Fetching agreement fee with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AgreementFeeScheduleDTO saveAgreementFeeSchedule(AgreementFeeScheduleDTO agreementFeeScheduleDTO) {
        log.info("Saving new agreement fee");
        AgreementFeeSchedule entity = mapper.toEntity(agreementFeeScheduleDTO);
        AgreementFeeSchedule saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AgreementFeeScheduleDTO updateAgreementFeeSchedule(Long id, AgreementFeeScheduleDTO agreementFeeScheduleDTO) {
        log.info("Updating agreement fee with ID: {}", id);

        AgreementFeeSchedule existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("agreement fee not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementFeeSchedule toUpdate = mapper.toEntity(agreementFeeScheduleDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementFeeSchedule updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAgreementFeeScheduleById(Long id) {
        log.info("Deleting agreement fee with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("agreement fee not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAgreementFeeScheduleById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
