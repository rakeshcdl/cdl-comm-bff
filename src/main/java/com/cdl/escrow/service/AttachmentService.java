package com.cdl.escrow.service;

import com.cdl.escrow.dto.AttachmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AttachmentService {

    Page<AttachmentDTO> getAllAttachment(final Pageable pageable);

    Optional<AttachmentDTO> getAttachmentById(Long id);

    AttachmentDTO saveAttachment(AttachmentDTO attachmentDTO);

    AttachmentDTO updateAttachment(Long id, AttachmentDTO attachmentDTO);

    Boolean deleteAttachmentById(Long id);

    boolean softDeleteAttachmentById(Long id);
}
