package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import com.cdl.escrow.entity.GeneralLedgerAccount;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneralLedgerAccountMapper extends EntityMapper<GeneralLedgerAccountDTO, GeneralLedgerAccount> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    GeneralLedgerAccountDTO toDto(GeneralLedgerAccount entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    GeneralLedgerAccount toEntity(GeneralLedgerAccountDTO dto);

}