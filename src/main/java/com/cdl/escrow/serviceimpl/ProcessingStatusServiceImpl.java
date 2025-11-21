package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ProcessingStatusDTO;
import com.cdl.escrow.service.ProcessingStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessingStatusServiceImpl implements ProcessingStatusService {
    @Override
    public Page<ProcessingStatusDTO> getAllProcessingStatus(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ProcessingStatusDTO> getProcessingStatusById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProcessingStatusDTO saveProcessingStatus(ProcessingStatusDTO processingStatusDTO) {
        return null;
    }

    @Override
    public ProcessingStatusDTO updateProcessingStatus(Long id, ProcessingStatusDTO processingStatusDTO) {
        return null;
    }

    @Override
    public Boolean deleteProcessingStatusById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteProcessingStatusById(Long id) {
        return false;
    }
}
