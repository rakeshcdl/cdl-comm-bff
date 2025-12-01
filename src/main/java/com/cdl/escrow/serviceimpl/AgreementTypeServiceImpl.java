package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementTypeDTO;
import com.cdl.escrow.entity.AgreementType;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementTypeMapper;
import com.cdl.escrow.repository.AgreementTypeRepository;
import com.cdl.escrow.service.AgreementTypeService;
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
public class AgreementTypeServiceImpl implements AgreementTypeService {

    private final AgreementTypeRepository repository;

    private final AgreementTypeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<AgreementTypeDTO> getAllAgreementType(Pageable pageable) {
        log.debug("Fetching all agreement type, page: {}", pageable.getPageNumber());
        Page<AgreementType> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgreementTypeDTO> getAgreementTypeById(Long id) {
        log.debug("Fetching agreement type with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AgreementTypeDTO saveAgreementType(AgreementTypeDTO agreementTypeDTO) {
        log.info("Saving new agreement type");
        AgreementType entity = mapper.toEntity(agreementTypeDTO);
        AgreementType saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AgreementTypeDTO updateAgreementType(Long id, AgreementTypeDTO agreementTypeDTO) {
        log.info("Updating agreement type with ID: {}", id);

        AgreementType existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("agreement type not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementType toUpdate = mapper.toEntity(agreementTypeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementType updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAgreementTypeById(Long id) {
        log.info("Deleting agreement type with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("agreement type not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAgreementTypeById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
