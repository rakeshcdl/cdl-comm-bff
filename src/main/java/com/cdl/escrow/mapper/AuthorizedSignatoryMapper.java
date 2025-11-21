package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import com.cdl.escrow.entity.AuthorizedSignatory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorizedSignatoryMapper extends EntityMapper<AuthorizedSignatoryDTO, AuthorizedSignatory> {

    @Mapping(source = "cifExists", target = "cifExistsDTO")
    @Mapping(source = "party", target = "partyDTO")
    AuthorizedSignatoryDTO toDto(AuthorizedSignatory entity);

    @Mapping(source = "cifExistsDTO", target = "cifExists")
    @Mapping(source = "partyDTO", target = "party")
    AuthorizedSignatory toEntity(AuthorizedSignatoryDTO dto);

}
