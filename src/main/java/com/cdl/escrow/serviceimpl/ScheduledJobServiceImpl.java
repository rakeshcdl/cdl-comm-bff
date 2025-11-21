package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ScheduledJobDTO;
import com.cdl.escrow.service.ScheduledJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledJobServiceImpl implements ScheduledJobService {
    @Override
    public Page<ScheduledJobDTO> getAllScheduledJob(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ScheduledJobDTO> getScheduledJobById(Long id) {
        return Optional.empty();
    }

    @Override
    public ScheduledJobDTO saveScheduledJob(ScheduledJobDTO scheduledJobDTO) {
        return null;
    }

    @Override
    public ScheduledJobDTO updateScheduledJob(Long id, ScheduledJobDTO scheduledJobDTO) {
        return null;
    }

    @Override
    public Boolean deleteScheduledJobById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteScheduledJobById(Long id) {
        return false;
    }
}
