package com.cdl.escrow.service;

import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BusinessSubSegmentService {

    Page<BusinessSubSegmentDTO> getAllBusinessSubSegment(final Pageable pageable);

    Optional<BusinessSubSegmentDTO> getBusinessSubSegmentById(Long id);

    BusinessSubSegmentDTO saveBusinessSubSegment(BusinessSubSegmentDTO businessSubSegmentDTO);

    BusinessSubSegmentDTO updateBusinessSubSegment(Long id, BusinessSubSegmentDTO businessSubSegmentDTO);

    Boolean deleteBusinessSubSegmentById(Long id);

    boolean softDeleteBusinessSubSegmentById(Long id);
}
