package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.EscrowAccountDTO;
import com.cdl.escrow.entity.EscrowAccount;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EscrowAccountMapper extends EntityMapper<EscrowAccountDTO, EscrowAccount> {

    EscrowAccountDTO toDto(EscrowAccount entity);

    EscrowAccount toEntity(EscrowAccountDTO dto);

}