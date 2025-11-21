package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BulkPaymentUploadDTO;
import com.cdl.escrow.entity.BulkPaymentUpload;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BulkPaymentUploadMapper;
import com.cdl.escrow.repository.BulkPaymentUploadRepository;
import com.cdl.escrow.service.BulkPaymentUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BulkPaymentUploadServiceImpl implements BulkPaymentUploadService {

    private final BulkPaymentUploadRepository repository;

    private final BulkPaymentUploadMapper mapper;


    @Override
    public Page<BulkPaymentUploadDTO> getAllBulkPaymentUpload(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<BulkPaymentUpload> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BulkPaymentUploadDTO> getBulkPaymentUploadById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BulkPaymentUploadDTO saveBulkPaymentUpload(BulkPaymentUploadDTO bulkPaymentUploadDTO) {
        log.info("Saving new application language code");
        BulkPaymentUpload entity = mapper.toEntity(bulkPaymentUploadDTO);
        BulkPaymentUpload saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BulkPaymentUploadDTO updateBulkPaymentUpload(Long id, BulkPaymentUploadDTO bulkPaymentUploadDTO) {
        log.info("Updating application language code with ID: {}", id);

        BulkPaymentUpload existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BulkPaymentUpload toUpdate = mapper.toEntity(bulkPaymentUploadDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        BulkPaymentUpload updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBulkPaymentUploadById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteBulkPaymentUploadById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
