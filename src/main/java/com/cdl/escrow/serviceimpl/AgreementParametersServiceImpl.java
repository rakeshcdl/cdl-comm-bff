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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementParametersServiceImpl implements AgreementParametersService {

    private final AgreementParametersRepository repository;

    private final AgreementParametersMapper mapper;



    @Override
    public Page<AgreementParametersDTO> getAllAgreementParameters(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AgreementParameters> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AgreementParametersDTO> getAgreementParameterById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AgreementParametersDTO saveAgreementParameter(AgreementParametersDTO agreementParametersDTO) {
        log.info("Saving new application language code");
        AgreementParameters entity = mapper.toEntity(agreementParametersDTO);
        AgreementParameters saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AgreementParametersDTO updateAgreementParameter(Long id, AgreementParametersDTO agreementParametersDTO) {
        log.info("Updating application language code with ID: {}", id);

        AgreementParameters existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementParameters toUpdate = mapper.toEntity(agreementParametersDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementParameters updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAgreementParameterById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAgreementParameterById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
