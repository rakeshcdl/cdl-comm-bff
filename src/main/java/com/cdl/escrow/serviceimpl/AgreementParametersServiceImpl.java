package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementParametersDTO;
import com.cdl.escrow.entity.AgreementParameters;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementParametersMapper;
import com.cdl.escrow.repository.AgreementParametersRepository;
import com.cdl.escrow.service.AgreementParametersService;
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
public class AgreementParametersServiceImpl implements AgreementParametersService {

    private final AgreementParametersRepository repository;

    private final AgreementParametersMapper mapper;



    @Override
    @Transactional(readOnly = true)
    public Page<AgreementParametersDTO> getAllAgreementParameters(Pageable pageable) {
        log.debug("Fetching all agreement parameter, page: {}", pageable.getPageNumber());
        Page<AgreementParameters> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgreementParametersDTO> getAgreementParameterById(Long id) {
        log.debug("Fetching agreement parameter with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AgreementParametersDTO saveAgreementParameter(AgreementParametersDTO agreementParametersDTO) {
        log.info("Saving new agreement parameter");
        AgreementParameters entity = mapper.toEntity(agreementParametersDTO);
        AgreementParameters saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AgreementParametersDTO updateAgreementParameter(Long id, AgreementParametersDTO agreementParametersDTO) {
        log.info("Updating agreement parameter with ID: {}", id);

        AgreementParameters existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("agreement parameter not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementParameters toUpdate = mapper.toEntity(agreementParametersDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementParameters updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAgreementParameterById(Long id) {
        log.info("Deleting agreement parameter with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("agreement parameter not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAgreementParameterById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
