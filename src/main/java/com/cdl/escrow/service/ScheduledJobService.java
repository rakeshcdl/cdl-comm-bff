package com.cdl.escrow.service;

import com.cdl.escrow.dto.ScheduledJobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScheduledJobService {

    Page<ScheduledJobDTO> getAllScheduledJob(final Pageable pageable);

    Optional<ScheduledJobDTO> getScheduledJobById(Long id);

    ScheduledJobDTO saveScheduledJob(ScheduledJobDTO scheduledJobDTO);

    ScheduledJobDTO updateScheduledJob(Long id, ScheduledJobDTO scheduledJobDTO);

    Boolean deleteScheduledJobById(Long id);

    boolean softDeleteScheduledJobById(Long id);
}
