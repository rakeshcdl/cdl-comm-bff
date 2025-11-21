package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementBudgetPlanDTO;
import com.cdl.escrow.entity.AgreementBudgetPlan;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementBudgetPlanMapper;
import com.cdl.escrow.repository.AgreementBudgetPlanRepository;
import com.cdl.escrow.service.AgreementBudgetPlanService;
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
public class AgreementBudgetPlanServiceImpl implements AgreementBudgetPlanService {

    private final AgreementBudgetPlanRepository repository;

    private final AgreementBudgetPlanMapper mapper;


    @Override
    public Page<AgreementBudgetPlanDTO> getAllAgreementBudgetPlan(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AgreementBudgetPlan> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AgreementBudgetPlanDTO> getAgreementBudgetPlanById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AgreementBudgetPlanDTO saveAgreementBudgetPlan(AgreementBudgetPlanDTO agreementBudgetPlanDTO) {
        log.info("Saving new application language code");
        AgreementBudgetPlan entity = mapper.toEntity(agreementBudgetPlanDTO);
        AgreementBudgetPlan saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AgreementBudgetPlanDTO updateAgreementBudgetPlan(Long id, AgreementBudgetPlanDTO agreementBudgetPlanDTO) {
        log.info("Updating application language code with ID: {}", id);

        AgreementBudgetPlan existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementBudgetPlan toUpdate = mapper.toEntity(agreementBudgetPlanDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementBudgetPlan updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAgreementBudgetPlanById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAgreementBudgetPlanById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
