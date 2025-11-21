package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ProductProgramDTO;
import com.cdl.escrow.service.ProductProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductProgramServiceImpl implements ProductProgramService {
    @Override
    public Page<ProductProgramDTO> getAllProductProgram(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ProductProgramDTO> getProductProgramById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductProgramDTO saveProductProgram(ProductProgramDTO productProgramDTO) {
        return null;
    }

    @Override
    public ProductProgramDTO updateProductProgram(Long id, ProductProgramDTO productProgramDTO) {
        return null;
    }

    @Override
    public Boolean deleteProductProgramById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteProductProgramById(Long id) {
        return false;
    }
}
