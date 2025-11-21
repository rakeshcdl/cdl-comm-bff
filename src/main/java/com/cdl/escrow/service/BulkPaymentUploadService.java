package com.cdl.escrow.service;

import com.cdl.escrow.dto.BulkPaymentUploadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BulkPaymentUploadService {

    Page<BulkPaymentUploadDTO> getAllBulkPaymentUpload(final Pageable pageable);

    Optional<BulkPaymentUploadDTO> getBulkPaymentUploadById(Long id);

    BulkPaymentUploadDTO saveBulkPaymentUpload(BulkPaymentUploadDTO bulkPaymentUploadDTO);

    BulkPaymentUploadDTO updateBulkPaymentUpload(Long id, BulkPaymentUploadDTO bulkPaymentUploadDTO);

    Boolean deleteBulkPaymentUploadById(Long id);

    boolean softDeleteBulkPaymentUploadById(Long id);
}
