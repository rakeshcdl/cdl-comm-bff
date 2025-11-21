package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AccountTypeDTO;
import com.cdl.escrow.entity.AccountType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper extends EntityMapper<AccountTypeDTO, AccountType> {


    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AccountTypeDTO toDto(AccountType entity);


    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AccountType toEntity(AccountTypeDTO dto);

}

