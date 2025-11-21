package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementSegmentRepository  extends JpaRepository<AgreementSegment, Long>, JpaSpecificationExecutor<AgreementSegment> {

    Optional<AgreementSegment> findByIdAndDeletedFalse(Long id);
}