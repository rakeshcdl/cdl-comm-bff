package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementSubTypeDTO;
import com.cdl.escrow.entity.AgreementSubType;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementSubTypeMapper;
import com.cdl.escrow.repository.AgreementSubTypeRepository;
import com.cdl.escrow.service.AgreementSubTypeService;
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
public class AgreementSubTypeServiceImpl implements AgreementSubTypeService {

    private final AgreementSubTypeRepository repository;

    private final AgreementSubTypeMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<AgreementSubTypeDTO> getAllAgreementSubType(Pageable pageable) {
        log.debug("Fetching all agreement sub type, page: {}", pageable.getPageNumber());
        Page<AgreementSubType> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgreementSubTypeDTO> getAgreementSubTypeById(Long id) {
        log.debug("Fetching agreement sub type with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AgreementSubTypeDTO saveAgreementSubType(AgreementSubTypeDTO agreementSubTypeDTO) {
        log.info("Saving new agreement sub type");
        AgreementSubType entity = mapper.toEntity(agreementSubTypeDTO);
        AgreementSubType saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AgreementSubTypeDTO updateAgreementSubType(Long id, AgreementSubTypeDTO agreementSubTypeDTO) {
        log.info("Updating agreement sub type with ID: {}", id);

        AgreementSubType existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("agreement sub type not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementSubType toUpdate = mapper.toEntity(agreementSubTypeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementSubType updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAgreementSubTypeById(Long id) {
        log.info("Deleting agreement sub type with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("agreement sub type not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAgreementSubTypeById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
