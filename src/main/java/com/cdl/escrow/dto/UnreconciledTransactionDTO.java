package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnreconciledTransactionDTO implements Serializable {
    private Long id;

    private String unReconTransactionId;
    private String transactionReferenceNumber;

    private BigDecimal transactionAmount;
    private BigDecimal totalTransactionAmount;

    private ZonedDateTime transactionDateTime;

    private String transactionNarration;
    private String transactionDescription;

    private Boolean discardFlag;
    private Boolean allocatedFlag;

    private String transactionParticular1;
    private String transactionParticular2;
    private String transactionParticularRemark1;
    private String transactionParticularRemark2;

    private String chequeReferenceNumber;

    private Boolean tasUpdateRequestedFlag;
    private Boolean tasUpdateAppliedFlag;

    private ZonedDateTime valueDateTime;
    private ZonedDateTime postedDateTime;
    private ZonedDateTime processingDateTime;

    private String branchIdentifierCode;
    private String postedBranchIdentifierCode;

    private String currencyCode;

    private String customField1;
    private String customField2;
    private String customField3;
    private String customField4;
    private String customField5;

    private String primaryUnitHolderFullName;

    private Boolean unallocatedCategoryFlag;

    private String tasPaymentStatusCode;

    private ZonedDateTime discardedDateTime;

    private Boolean creditedToEscrowFlag;

    private String coreBankingResponsePayload;

    private String paymentReferenceNumber;
    private String subBucketIdentifier;

    private EscrowAgreementDTO escrowAgreementDTO;

    private ApplicationSettingDTO bucketTypeDTO;

    private ApplicationSettingDTO subBucketTypeDTO;

    private EscrowAccountDTO escrowAccountDTO;

    private ApplicationSettingDTO depositModeDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;

}
