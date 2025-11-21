package com.cdl.escrow.repository;

import com.cdl.escrow.entity.BusinessSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessSegmentRepository extends JpaRepository<BusinessSegment, Long>, JpaSpecificationExecutor<BusinessSegment> {

    Optional<BusinessSegment> findByIdAndDeletedFalse(Long id);
}
