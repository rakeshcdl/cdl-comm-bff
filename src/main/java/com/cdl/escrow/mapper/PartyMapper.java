package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.entity.Party;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartyMapper extends EntityMapper<PartyDTO, Party> {

    @Mapping(source = "partyConstituent", target = "partyConstituentDTO")
    @Mapping(source = "role", target = "roleDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    PartyDTO toDto(Party entity);

    @Mapping(source = "partyConstituentDTO", target = "partyConstituent")
    @Mapping(source = "roleDTO", target = "role")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    Party toEntity(PartyDTO dto);

}