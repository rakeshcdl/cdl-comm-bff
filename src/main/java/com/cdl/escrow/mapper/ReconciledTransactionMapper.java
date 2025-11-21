package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ReconciledTransactionDTO;
import com.cdl.escrow.entity.ReconciledTransaction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReconciledTransactionMapper extends EntityMapper<ReconciledTransactionDTO, ReconciledTransaction> {

    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    @Mapping(source = "bucketType", target = "bucketTypeDTO")
    @Mapping(source = "subBucketType", target = "subBucketTypeDTO")
    @Mapping(source = "depositMode", target = "depositModeDTO")
    @Mapping(source = "escrowAccount", target = "escrowAccountDTO")
    @Mapping(source = "nonReconTransaction", target = "nonReconTransactionDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    ReconciledTransactionDTO toDto(ReconciledTransaction entity);

    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    @Mapping(source = "bucketTypeDTO", target = "bucketType")
    @Mapping(source = "subBucketTypeDTO", target = "subBucketType")
    @Mapping(source = "depositModeDTO", target = "depositMode")
    @Mapping(source = "escrowAccountDTO", target = "escrowAccount")
    @Mapping(source = "nonReconTransactionDTO", target = "nonReconTransaction")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    ReconciledTransaction toEntity(ReconciledTransactionDTO dto);

}
