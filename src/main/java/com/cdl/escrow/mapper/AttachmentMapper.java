package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AttachmentDTO;
import com.cdl.escrow.entity.Attachment;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {

    @Mapping(source = "partyDocument", target = "partyDocumentDTO")
    @Mapping(source = "documentType", target = "documentTypeDTO")
    AttachmentDTO toDto(Attachment entity);

    @Mapping(source = "partyDocumentDTO", target = "partyDocument")
    @Mapping(source = "documentTypeDTO", target = "documentType")
    Attachment toEntity(AttachmentDTO dto);

}
