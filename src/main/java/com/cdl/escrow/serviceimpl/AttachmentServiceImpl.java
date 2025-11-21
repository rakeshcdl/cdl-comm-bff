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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;

    private final AttachmentMapper mapper;


    @Override
    public Page<AttachmentDTO> getAllAttachment(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<Attachment> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AttachmentDTO> getAttachmentById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AttachmentDTO saveAttachment(AttachmentDTO attachmentDTO) {
        log.info("Saving new application language code");
        Attachment entity = mapper.toEntity(attachmentDTO);
        Attachment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AttachmentDTO updateAttachment(Long id, AttachmentDTO attachmentDTO) {
        log.info("Updating application language code with ID: {}", id);

        Attachment existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Attachment toUpdate = mapper.toEntity(attachmentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Attachment updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAttachmentById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAttachmentById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
