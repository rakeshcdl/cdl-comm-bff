package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.entity.Party;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartyMapper extends EntityMapper<PartyDTO, Party> {

    PartyDTO toDto(Party entity);

    Party toEntity(PartyDTO dto);

}