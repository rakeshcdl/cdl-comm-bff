package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import com.cdl.escrow.entity.AccountPurpose;
import com.cdl.escrow.entity.AgreementFeeSchedule;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AccountPurposeMapper;
import com.cdl.escrow.mapper.AgreementFeeScheduleMapper;
import com.cdl.escrow.repository.AccountPurposeRepository;
import com.cdl.escrow.repository.AgreementFeeScheduleRepository;
import com.cdl.escrow.service.AgreementFeeScheduleService;
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
public class AgreementFeeScheduleServiceImpl implements AgreementFeeScheduleService {

    private final AgreementFeeScheduleRepository repository;

    private final AgreementFeeScheduleMapper mapper;

    @Override
    public Page<AgreementFeeScheduleDTO> getAllAgreementFeeSchedule(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AgreementFeeSchedule> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AgreementFeeScheduleDTO> getAgreementFeeScheduleById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AgreementFeeScheduleDTO saveAgreementFeeSchedule(AgreementFeeScheduleDTO agreementFeeScheduleDTO) {
        log.info("Saving new application language code");
        AgreementFeeSchedule entity = mapper.toEntity(agreementFeeScheduleDTO);
        AgreementFeeSchedule saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AgreementFeeScheduleDTO updateAgreementFeeSchedule(Long id, AgreementFeeScheduleDTO agreementFeeScheduleDTO) {
        log.info("Updating application language code with ID: {}", id);

        AgreementFeeSchedule existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementFeeSchedule toUpdate = mapper.toEntity(agreementFeeScheduleDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementFeeSchedule updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAgreementFeeScheduleById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAgreementFeeScheduleById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
