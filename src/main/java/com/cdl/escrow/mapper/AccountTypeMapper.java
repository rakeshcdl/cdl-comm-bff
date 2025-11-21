package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AccountTypeDTO;
import com.cdl.escrow.entity.AccountType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper extends EntityMapper<AccountTypeDTO, AccountType> {

    AccountTypeDTO toDto(AccountType entity);

    AccountType toEntity(AccountTypeDTO dto);

}

