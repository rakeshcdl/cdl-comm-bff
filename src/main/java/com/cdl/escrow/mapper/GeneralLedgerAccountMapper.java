package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import com.cdl.escrow.entity.GeneralLedgerAccount;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralLedgerAccountMapper extends EntityMapper<GeneralLedgerAccountDTO, GeneralLedgerAccount> {

    GeneralLedgerAccountDTO toDto(GeneralLedgerAccount entity);

    GeneralLedgerAccount toEntity(GeneralLedgerAccountDTO dto);

}