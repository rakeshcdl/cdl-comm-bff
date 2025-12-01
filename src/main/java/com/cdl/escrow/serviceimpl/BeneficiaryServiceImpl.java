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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository repository;

    private final BeneficiaryMapper mapper;



    @Override
    @Transactional(readOnly = true)
    public Page<BeneficiaryDTO> getAllBeneficiary(Pageable pageable) {
        log.debug("Fetching all beneficiary, page: {}", pageable.getPageNumber());
        Page<Beneficiary> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeneficiaryDTO> getBeneficiaryById(Long id) {
        log.debug("Fetching beneficiary with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public BeneficiaryDTO saveBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        log.info("Saving new beneficiary");
        Beneficiary entity = mapper.toEntity(beneficiaryDTO);
        Beneficiary saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public BeneficiaryDTO updateBeneficiary(Long id, BeneficiaryDTO beneficiaryDTO) {
        log.info("Updating beneficiary with ID: {}", id);

        Beneficiary existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("beneficiary not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Beneficiary toUpdate = mapper.toEntity(beneficiaryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Beneficiary updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteBeneficiaryById(Long id) {
        log.info("Deleting beneficiary with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("beneficiary not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteBeneficiaryById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
