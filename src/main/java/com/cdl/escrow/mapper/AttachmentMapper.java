package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AttachmentDTO;
import com.cdl.escrow.entity.Attachment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {

    AttachmentDTO toDto(Attachment entity);

    Attachment toEntity(AttachmentDTO dto);

}
