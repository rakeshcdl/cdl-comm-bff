package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AccountPurposeDTO;
import com.cdl.escrow.entity.AccountPurpose;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountPurposeMapper extends EntityMapper<AccountPurposeDTO, AccountPurpose> {

    @Mapping(source = "criticality", target = "criticalityDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AccountPurposeDTO toDto(AccountPurpose entity);

    @Mapping(source = "criticalityDTO", target = "criticality")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AccountPurpose toEntity(AccountPurposeDTO dto);

}
