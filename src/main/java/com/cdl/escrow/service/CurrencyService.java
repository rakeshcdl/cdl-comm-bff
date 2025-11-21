package com.cdl.escrow.service;

import com.cdl.escrow.dto.CurrencyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CurrencyService {

    Page<CurrencyDTO> getAllCurrency(final Pageable pageable);

    Optional<CurrencyDTO> getCurrencyById(Long id);

    CurrencyDTO saveCurrency(CurrencyDTO currencyDTO);

    CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO);

    Boolean deleteCurrencyById(Long id);

    boolean softDeleteCurrencyById(Long id);
}
