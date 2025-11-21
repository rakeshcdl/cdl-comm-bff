package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BeneficiaryDTO;
import com.cdl.escrow.entity.Beneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper extends EntityMapper<BeneficiaryDTO, Beneficiary> {

    BeneficiaryDTO toDto(Beneficiary entity);

    Beneficiary toEntity(BeneficiaryDTO dto);

}
