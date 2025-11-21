package com.cdl.escrow.repository;

import com.cdl.escrow.entity.BusinessSubSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessSubSegmentRepository extends JpaRepository<BusinessSubSegment, Long>, JpaSpecificationExecutor<BusinessSubSegment> {

    Optional<BusinessSubSegment> findByIdAndDeletedFalse(Long id);
}