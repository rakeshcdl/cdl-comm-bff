package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.InvestmentDTO;
import com.cdl.escrow.entity.Investment;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.InvestmentMapper;
import com.cdl.escrow.repository.InvestmentRepository;
import com.cdl.escrow.service.InvestmentService;
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
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository repository;

    private final InvestmentMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<InvestmentDTO> getAllInvestment(Pageable pageable) {
        log.debug("Fetching all investment, page: {}", pageable.getPageNumber());
        Page<Investment> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvestmentDTO> getInvestmentById(Long id) {
        log.debug("Fetching investment with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public InvestmentDTO saveInvestment(InvestmentDTO investmentDTO) {
        log.info("Saving new investment");
        Investment entity = mapper.toEntity(investmentDTO);
        Investment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public InvestmentDTO updateInvestment(Long id, InvestmentDTO investmentDTO) {
        log.info("Updating investment with ID: {}", id);

        Investment existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("investment not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Investment toUpdate = mapper.toEntity(investmentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Investment updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteInvestmentById(Long id) {
        log.info("Deleting investment with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("investment not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteInvestmentById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
