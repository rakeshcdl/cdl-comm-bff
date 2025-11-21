package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AccountTypeCategoryDTO;
import com.cdl.escrow.entity.AccountTypeCategory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountTypeCategoryMapper extends EntityMapper<AccountTypeCategoryDTO, AccountTypeCategory> {

    @Mapping(source = "accountType", target = "accountTypeDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AccountTypeCategoryDTO toDto(AccountTypeCategory entity);

    @Mapping(source = "accountTypeDTO", target = "accountType")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AccountTypeCategory toEntity(AccountTypeCategoryDTO dto);

}
