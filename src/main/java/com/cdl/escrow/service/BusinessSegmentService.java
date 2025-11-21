package com.cdl.escrow.service;

import com.cdl.escrow.dto.BusinessSegmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BusinessSegmentService {

    Page<BusinessSegmentDTO> getAllBusinessSegment(final Pageable pageable);

    Optional<BusinessSegmentDTO> getBusinessSegmentById(Long id);

    BusinessSegmentDTO saveBusinessSegment(BusinessSegmentDTO businessSegmentDTO);

    BusinessSegmentDTO updateBusinessSegment(Long id, BusinessSegmentDTO businessSegmentDTO);

    Boolean deleteBusinessSegmentById(Long id);

    boolean softDeleteBusinessSegmentById(Long id);
}
