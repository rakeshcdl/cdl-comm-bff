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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementSubTypeServiceImpl implements AgreementSubTypeService {

    private final AgreementSubTypeRepository repository;

    private final AgreementSubTypeMapper mapper;


    @Override
    public Page<AgreementSubTypeDTO> getAllAgreementSubType(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AgreementSubType> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AgreementSubTypeDTO> getAgreementSubTypeById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AgreementSubTypeDTO saveAgreementSubType(AgreementSubTypeDTO agreementSubTypeDTO) {
        log.info("Saving new application language code");
        AgreementSubType entity = mapper.toEntity(agreementSubTypeDTO);
        AgreementSubType saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AgreementSubTypeDTO updateAgreementSubType(Long id, AgreementSubTypeDTO agreementSubTypeDTO) {
        log.info("Updating application language code with ID: {}", id);

        AgreementSubType existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementSubType toUpdate = mapper.toEntity(agreementSubTypeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementSubType updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAgreementSubTypeById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAgreementSubTypeById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
