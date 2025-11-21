package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.entity.PartyDocument;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartyDocumentMapper extends EntityMapper<PartyDocumentDTO, PartyDocument> {

    @Mapping(source = "beneficiary", target = "beneficiaryDTO")
    @Mapping(source = "party", target = "partyDTO")
    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    @Mapping(source = "unitaryPayment", target = "unitaryPaymentDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    PartyDocumentDTO toDto(PartyDocument entity);


    @Mapping(source = "beneficiaryDTO", target = "beneficiary")
    @Mapping(source = "partyDTO", target = "party")
    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    @Mapping(source = "unitaryPaymentDTO", target = "unitaryPayment")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    PartyDocument toEntity(PartyDocumentDTO dto);

}