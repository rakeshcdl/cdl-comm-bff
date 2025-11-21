package com.cdl.escrow.service;

import com.cdl.escrow.dto.ProcessingStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProcessingStatusService {

    Page<ProcessingStatusDTO> getAllProcessingStatus(final Pageable pageable);

    Optional<ProcessingStatusDTO> getProcessingStatusById(Long id);

    ProcessingStatusDTO saveProcessingStatus(ProcessingStatusDTO processingStatusDTO);

    ProcessingStatusDTO updateProcessingStatus(Long id, ProcessingStatusDTO processingStatusDTO);

    Boolean deleteProcessingStatusById(Long id);

    boolean softDeleteProcessingStatusById(Long id);
}
