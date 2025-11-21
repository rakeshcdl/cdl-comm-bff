package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BeneficiaryDTO;
import com.cdl.escrow.entity.Beneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper extends EntityMapper<BeneficiaryDTO, Beneficiary> {

    @Mapping(source = "accountType", target = "accountTypeDTO")
    @Mapping(source = "transferType", target = "transferTypeDTO")
    @Mapping(source = "role", target = "roleDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    BeneficiaryDTO toDto(Beneficiary entity);


    @Mapping(source = "accountTypeDTO", target = "accountType")
    @Mapping(source = "transferTypeDTO", target = "transferType")
    @Mapping(source = "roleDTO", target = "role")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    Beneficiary toEntity(BeneficiaryDTO dto);

}
