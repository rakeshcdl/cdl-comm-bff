package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementSignatoryDTO;
import com.cdl.escrow.entity.AgreementSignatory;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementSignatoryMapper;
import com.cdl.escrow.repository.AgreementSignatoryRepository;
import com.cdl.escrow.service.AgreementSignatoryService;
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
public class AgreementSignatoryServiceImpl implements AgreementSignatoryService {

    private final AgreementSignatoryRepository repository;

    private final AgreementSignatoryMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<AgreementSignatoryDTO> getAllAgreementSignatory(Pageable pageable) {
        log.debug("Fetching all agreement signatory, page: {}", pageable.getPageNumber());
        Page<AgreementSignatory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgreementSignatoryDTO> getAgreementSignatoryById(Long id) {
        log.debug("Fetching agreement signatory with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AgreementSignatoryDTO saveAgreementSignatory(AgreementSignatoryDTO agreementSignatoryDTO) {
        log.info("Saving new agreement signatory");
        AgreementSignatory entity = mapper.toEntity(agreementSignatoryDTO);
        AgreementSignatory saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AgreementSignatoryDTO updateAgreementSignatory(Long id, AgreementSignatoryDTO agreementSignatoryDTO) {
        log.info("Updating agreement signatorye with ID: {}", id);

        AgreementSignatory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("agreement signatory not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementSignatory toUpdate = mapper.toEntity(agreementSignatoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementSignatory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAgreementSignatoryById(Long id) {
        log.info("Deleting agreement signatory with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("agreement signatory not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAgreementSignatoryById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
