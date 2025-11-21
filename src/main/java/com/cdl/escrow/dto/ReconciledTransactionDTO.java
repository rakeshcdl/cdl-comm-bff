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
public class ReconciledTransactionDTO implements Serializable {

    private Long id;

    private String reconTransactionId;

    private BigDecimal transactionAmount;

    private BigDecimal totalTransactionAmount; // if applicable

    private ZonedDateTime transactionDateTime;

    private String transactionNarration;

    private String transactionDescription;

    private String processingRemarks;

    private Boolean allocatedFlag;

    private String transactionParticular1;

    private String transactionParticular2;

    private String transactionParticularRemark1;

    private String transactionParticularRemark2;

    private String chequeReferenceNumber;

    private Boolean tasUpdateRequestedFlag;

    private Boolean tasUpdateAppliedFlag;

    private Boolean tasUpdateEnabledFlag; // optional: use only if this is a config toggle

    private String unitReferenceNumber;

    private String tasPaymentStatusCode;

    private String batchTransactionId;

    private String reconciliationResponsePayload;

    private Boolean rollbackFlag;

    private String coreBankingResponsePayload;

    private String paymentReferenceNumber;

    private String subBucketIdentifier;

    private EscrowAgreementDTO escrowAgreementDTO;

    private ApplicationSettingDTO bucketTypeDTO;

    private ApplicationSettingDTO subBucketTypeDTO;

    private ApplicationSettingDTO depositModeDTO;

    private EscrowAccountDTO escrowAccountDTO;

    private UnreconciledTransactionDTO nonReconTransactionDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
