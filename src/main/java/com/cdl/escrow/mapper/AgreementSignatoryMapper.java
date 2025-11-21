package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementSignatoryDTO;
import com.cdl.escrow.entity.AgreementSignatory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementSignatoryMapper extends EntityMapper<AgreementSignatoryDTO, AgreementSignatory> {

    @Mapping(source = "authorizedSignatory", target = "authorizedSignatoryDTO")
    @Mapping(source = "party", target = "partyDTO")
    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    AgreementSignatoryDTO toDto(AgreementSignatory entity);

    @Mapping(source = "authorizedSignatoryDTO", target = "authorizedSignatory")
    @Mapping(source = "partyDTO", target = "party")
    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    AgreementSignatory toEntity(AgreementSignatoryDTO dto);

}