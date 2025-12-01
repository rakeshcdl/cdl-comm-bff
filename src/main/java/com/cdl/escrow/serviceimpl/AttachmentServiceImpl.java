package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AttachmentDTO;
import com.cdl.escrow.entity.Attachment;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AttachmentMapper;
import com.cdl.escrow.repository.AttachmentRepository;
import com.cdl.escrow.service.AttachmentService;
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
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;

    private final AttachmentMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<AttachmentDTO> getAllAttachment(Pageable pageable) {
        log.debug("Fetching all attachment, page: {}", pageable.getPageNumber());
        Page<Attachment> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AttachmentDTO> getAttachmentById(Long id) {
        log.debug("Fetching attachment with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public AttachmentDTO saveAttachment(AttachmentDTO attachmentDTO) {
        log.info("Saving new attachment");
        Attachment entity = mapper.toEntity(attachmentDTO);
        Attachment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public AttachmentDTO updateAttachment(Long id, AttachmentDTO attachmentDTO) {
        log.info("Updating attachment with ID: {}", id);

        Attachment existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("attachment not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Attachment toUpdate = mapper.toEntity(attachmentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Attachment updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteAttachmentById(Long id) {
        log.info("Deleting attachment with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("attachment not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteAttachmentById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
