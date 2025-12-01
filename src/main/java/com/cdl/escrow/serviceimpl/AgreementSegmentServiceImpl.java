package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AgreementSegmentDTO;
import com.cdl.escrow.entity.AgreementSegment;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AgreementSegmentMapper;
import com.cdl.escrow.repository.AgreementSegmentRepository;
import com.cdl.escrow.service.AgreementSegmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementSegmentServiceImpl implements AgreementSegmentService {

    private final AgreementSegmentRepository repository;

    private final AgreementSegmentMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<AgreementSegmentDTO> getAllAgreementSegment(Pageable pageable) {
        log.debug("Fetching all agreement segment, page: {}", pageable.getPageNumber());
        Page<AgreementSegment> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgreementSegmentDTO> getAgreementSegmentById(Long id) {
        log.debug("Fetching agreement segment with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AgreementSegmentDTO saveAgreementSegment(AgreementSegmentDTO agreementSegmentDTO) {
        log.info("Saving new agreement segment");
        AgreementSegment entity = mapper.toEntity(agreementSegmentDTO);
        AgreementSegment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AgreementSegmentDTO updateAgreementSegment(Long id, AgreementSegmentDTO agreementSegmentDTO) {
        log.info("Updating agreement segment with ID: {}", id);

        AgreementSegment existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AgreementSegment toUpdate = mapper.toEntity(agreementSegmentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AgreementSegment updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAgreementSegmentById(Long id) {
        log.info("Deleting agreement segment with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("agreement segment not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAgreementSegmentById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
