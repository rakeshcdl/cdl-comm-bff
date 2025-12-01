package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ProductProgramDTO;
import com.cdl.escrow.entity.ProductProgram;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ProductProgramMapper;
import com.cdl.escrow.repository.ProductProgramRepository;
import com.cdl.escrow.service.ProductProgramService;
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
public class ProductProgramServiceImpl implements ProductProgramService {

    private final ProductProgramRepository repository;

    private final ProductProgramMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductProgramDTO> getAllProductProgram(Pageable pageable) {
        log.debug("Fetching all product program, page: {}", pageable.getPageNumber());
        Page<ProductProgram> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductProgramDTO> getProductProgramById(Long id) {
        log.debug("Fetching product program with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ProductProgramDTO saveProductProgram(ProductProgramDTO productProgramDTO) {
        log.info("Saving new product program");
        ProductProgram entity = mapper.toEntity(productProgramDTO);
        ProductProgram saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ProductProgramDTO updateProductProgram(Long id, ProductProgramDTO productProgramDTO) {
        log.info("Updating product program with ID: {}", id);

        ProductProgram existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("product program not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ProductProgram toUpdate = mapper.toEntity(productProgramDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ProductProgram updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteProductProgramById(Long id) {
        log.info("Deleting product program with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("product program not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteProductProgramById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
