package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.InvestmentDTO;
import com.cdl.escrow.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestmentServiceImpl implements InvestmentService {
    @Override
    public Page<InvestmentDTO> getAllInvestment(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<InvestmentDTO> getInvestmentById(Long id) {
        return Optional.empty();
    }

    @Override
    public InvestmentDTO saveInvestment(InvestmentDTO investmentDTO) {
        return null;
    }

    @Override
    public InvestmentDTO updateInvestment(Long id, InvestmentDTO investmentDTO) {
        return null;
    }

    @Override
    public Boolean deleteInvestmentById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteInvestmentById(Long id) {
        return false;
    }
}
