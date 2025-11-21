package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BulkPaymentUploadDTO;
import com.cdl.escrow.entity.BulkPaymentUpload;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BulkPaymentUploadMapper extends EntityMapper<BulkPaymentUploadDTO, BulkPaymentUpload> {

    @Mapping(source = "party", target = "partyDTO")
    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    @Mapping(source = "escrowAccount", target = "escrowAccountDTO")
    @Mapping(source = "transactionType", target = "transactionTypeDTO")
    @Mapping(source = "currency", target = "currencyDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    @Mapping(source = "beneficiary", target = "beneficiaryDTO")
    BulkPaymentUploadDTO toDto(BulkPaymentUpload entity);


    @Mapping(source = "partyDTO", target = "party")
    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    @Mapping(source = "escrowAccountDTO", target = "escrowAccount")
    @Mapping(source = "transactionTypeDTO", target = "transactionType")
    @Mapping(source = "currencyDTO", target = "currency")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    @Mapping(source = "beneficiaryDTO", target = "beneficiary")
    BulkPaymentUpload toEntity(BulkPaymentUploadDTO dto);

}
