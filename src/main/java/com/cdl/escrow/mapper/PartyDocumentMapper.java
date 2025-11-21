package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.entity.PartyDocument;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartyDocumentMapper extends EntityMapper<PartyDocumentDTO, PartyDocument> {

    PartyDocumentDTO toDto(PartyDocument entity);

    PartyDocument toEntity(PartyDocumentDTO dto);

}