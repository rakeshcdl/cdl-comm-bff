package com.cdl.escrow.repository;

import com.cdl.escrow.entity.BulkPaymentUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BulkPaymentUploadRepository extends JpaRepository<BulkPaymentUpload, Long>, JpaSpecificationExecutor<BulkPaymentUpload> {

    Optional<BulkPaymentUpload> findByIdAndDeletedFalse(Long id);
}