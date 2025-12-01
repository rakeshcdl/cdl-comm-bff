package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.entity.EscrowAgreement;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.EscrowAgreementMapper;
import com.cdl.escrow.repository.EscrowAgreementRepository;
import com.cdl.escrow.service.EscrowAgreementService;
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
public class EscrowAgreementServiceImpl implements EscrowAgreementService {

    private final EscrowAgreementRepository repository;

    private final EscrowAgreementMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<EscrowAgreementDTO> getAllEscrowAgreement(Pageable pageable) {
        log.debug("Fetching all escrow agreement, page: {}", pageable.getPageNumber());
        Page<EscrowAgreement> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EscrowAgreementDTO> getEscrowAgreementById(Long id) {
        log.debug("Fetching escrow agreement with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public EscrowAgreementDTO saveEscrowAgreement(EscrowAgreementDTO escrowAgreementDTO) {
        log.info("Saving new escrow agreement");
        EscrowAgreement entity = mapper.toEntity(escrowAgreementDTO);
        EscrowAgreement saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public EscrowAgreementDTO updateEscrowAgreement(Long id, EscrowAgreementDTO escrowAgreementDTO) {
        log.info("Updating escrow agreement with ID: {}", id);

        EscrowAgreement existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("escrow agreement not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        EscrowAgreement toUpdate = mapper.toEntity(escrowAgreementDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        EscrowAgreement updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteEscrowAgreementById(Long id) {
        log.info("Deleting escrow agreement with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("escrow agreement not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteEscrowAgreementById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
