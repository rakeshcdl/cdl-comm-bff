package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BeneficiaryDTO;
import com.cdl.escrow.entity.Beneficiary;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BeneficiaryMapper;
import com.cdl.escrow.repository.BeneficiaryRepository;
import com.cdl.escrow.service.BeneficiaryService;
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
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository repository;

    private final BeneficiaryMapper mapper;



    @Override
    public Page<BeneficiaryDTO> getAllBeneficiary(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<Beneficiary> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BeneficiaryDTO> getBeneficiaryById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BeneficiaryDTO saveBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        log.info("Saving new application language code");
        Beneficiary entity = mapper.toEntity(beneficiaryDTO);
        Beneficiary saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BeneficiaryDTO updateBeneficiary(Long id, BeneficiaryDTO beneficiaryDTO) {
        log.info("Updating application language code with ID: {}", id);

        Beneficiary existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Beneficiary toUpdate = mapper.toEntity(beneficiaryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Beneficiary updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBeneficiaryById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteBeneficiaryById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
