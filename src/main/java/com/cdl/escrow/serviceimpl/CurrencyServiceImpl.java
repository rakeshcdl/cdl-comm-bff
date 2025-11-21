package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.CurrencyDTO;
import com.cdl.escrow.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public Page<CurrencyDTO> getAllCurrency(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CurrencyDTO> getCurrencyById(Long id) {
        return Optional.empty();
    }

    @Override
    public CurrencyDTO saveCurrency(CurrencyDTO currencyDTO) {
        return null;
    }

    @Override
    public CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO) {
        return null;
    }

    @Override
    public Boolean deleteCurrencyById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteCurrencyById(Long id) {
        return false;
    }
}
