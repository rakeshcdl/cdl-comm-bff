package com.cdl.escrow.service;

import com.cdl.escrow.dto.InvestmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InvestmentService {

    Page<InvestmentDTO> getAllInvestment(final Pageable pageable);

    Optional<InvestmentDTO> getInvestmentById(Long id);

    InvestmentDTO saveInvestment(InvestmentDTO investmentDTO);

    InvestmentDTO updateInvestment(Long id, InvestmentDTO investmentDTO);

    Boolean deleteInvestmentById(Long id);

    boolean softDeleteInvestmentById(Long id);
}
