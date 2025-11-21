package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import com.cdl.escrow.mapper.AccountPurposeMapper;
import com.cdl.escrow.repository.AccountPurposeRepository;
import com.cdl.escrow.repository.BusinessSubSegmentRepository;
import com.cdl.escrow.service.BusinessSubSegmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessSubSegmentServiceImpl implements BusinessSubSegmentService {

    private final BusinessSubSegmentRepository repository;

    private final AccountPurposeMapper mapper;




    @Override
    public Page<BusinessSubSegmentDTO> getAllBusinessSubSegment(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BusinessSubSegmentDTO> getBusinessSubSegmentById(Long id) {
        return Optional.empty();
    }

    @Override
    public BusinessSubSegmentDTO saveBusinessSubSegment(BusinessSubSegmentDTO businessSubSegmentDTO) {
        return null;
    }

    @Override
    public BusinessSubSegmentDTO updateBusinessSubSegment(Long id, BusinessSubSegmentDTO businessSubSegmentDTO) {
        return null;
    }

    @Override
    public Boolean deleteBusinessSubSegmentById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteBusinessSubSegmentById(Long id) {
        return false;
    }
}
