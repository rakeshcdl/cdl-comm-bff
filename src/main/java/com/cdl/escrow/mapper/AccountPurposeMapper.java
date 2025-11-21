package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AccountPurposeDTO;
import com.cdl.escrow.entity.AccountPurpose;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountPurposeMapper extends EntityMapper<AccountPurposeDTO, AccountPurpose> {

    AccountPurposeDTO toDto(AccountPurpose entity);

    AccountPurpose toEntity(AccountPurposeDTO dto);

}
