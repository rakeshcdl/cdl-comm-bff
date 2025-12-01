package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.CurrencyDTO;
import com.cdl.escrow.entity.Currency;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.CurrencyMapper;
import com.cdl.escrow.repository.CurrencyRepository;
import com.cdl.escrow.service.CurrencyService;
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
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository repository;

    private final CurrencyMapper mapper;



    @Override
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> getAllCurrency(Pageable pageable) {
        log.debug("Fetching all currency, page: {}", pageable.getPageNumber());
        Page<Currency> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> getCurrencyById(Long id) {
        log.debug("Fetching currency with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public CurrencyDTO saveCurrency(CurrencyDTO currencyDTO) {
        log.info("Saving new currency");
        Currency entity = mapper.toEntity(currencyDTO);
        Currency saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO) {
        log.info("Updating currency with ID: {}", id);

        Currency existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("currency not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Currency toUpdate = mapper.toEntity(currencyDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Currency updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteCurrencyById(Long id) {
        log.info("Deleting currency with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("currency not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteCurrencyById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
