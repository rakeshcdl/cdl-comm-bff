package com.cdl.escrow.service;

import com.cdl.escrow.dto.ProductProgramDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductProgramService {

    Page<ProductProgramDTO> getAllProductProgram(final Pageable pageable);

    Optional<ProductProgramDTO> getProductProgramById(Long id);

    ProductProgramDTO saveProductProgram(ProductProgramDTO productProgramDTO);

    ProductProgramDTO updateProductProgram(Long id, ProductProgramDTO productProgramDTO);

    Boolean deleteProductProgramById(Long id);

    boolean softDeleteProductProgramById(Long id);
}
