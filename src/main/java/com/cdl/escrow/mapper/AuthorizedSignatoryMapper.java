package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import com.cdl.escrow.entity.AuthorizedSignatory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorizedSignatoryMapper extends EntityMapper<AuthorizedSignatoryDTO, AuthorizedSignatory> {

    AuthorizedSignatoryDTO toDto(AuthorizedSignatory entity);

    AuthorizedSignatory toEntity(AuthorizedSignatoryDTO dto);

}
