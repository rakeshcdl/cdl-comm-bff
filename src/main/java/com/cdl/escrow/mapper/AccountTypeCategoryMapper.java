package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AccountTypeCategoryDTO;
import com.cdl.escrow.entity.AccountTypeCategory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeCategoryMapper extends EntityMapper<AccountTypeCategoryDTO, AccountTypeCategory> {

    AccountTypeCategoryDTO toDto(AccountTypeCategory entity);

    AccountTypeCategory toEntity(AccountTypeCategoryDTO dto);

}
