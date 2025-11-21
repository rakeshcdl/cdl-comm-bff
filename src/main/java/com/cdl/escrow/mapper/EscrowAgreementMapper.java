package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.entity.EscrowAgreement;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EscrowAgreementMapper extends EntityMapper<EscrowAgreementDTO, EscrowAgreement> {

    EscrowAgreementDTO toDto(EscrowAgreement entity);

    EscrowAgreement toEntity(EscrowAgreementDTO dto);

}