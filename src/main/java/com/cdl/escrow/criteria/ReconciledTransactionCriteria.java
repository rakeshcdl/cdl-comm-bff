package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ReconciledTransactionCriteria implements Serializable {

    private LongFilter id;

    private StringFilter reconTransactionId;

    private BigDecimalFilter transactionAmount;

    private BigDecimalFilter totalTransactionAmount; // if applicable

    private ZonedDateTimeFilter transactionDateTime;

    private StringFilter transactionNarration;

    private StringFilter transactionDescription;

    private StringFilter processingRemarks;

    private BooleanFilter allocatedFlag;

    private StringFilter transactionParticular1;

    private StringFilter transactionParticular2;

    private StringFilter transactionParticularRemark1;

    private StringFilter transactionParticularRemark2;

    private StringFilter chequeReferenceNumber;

    private BooleanFilter tasUpdateRequestedFlag;

    private BooleanFilter tasUpdateAppliedFlag;

    private BooleanFilter tasUpdateEnabledFlag; // optional: use only if this is a config toggle

    private StringFilter unitReferenceNumber;

    private StringFilter tasPaymentStatusCode;

    private StringFilter batchTransactionId;

    private StringFilter reconciliationResponsePayload;

    private BooleanFilter rollbackFlag;

    private StringFilter coreBankingResponsePayload;

    private StringFilter paymentReferenceNumber;

    private StringFilter subBucketIdentifier;

    private LongFilter escrowAgreementId;

    private LongFilter bucketTypeId;

    private LongFilter subBucketTypeId;

    private LongFilter depositModeId;

    private LongFilter escrowAccountId;

    private LongFilter nonReconTransactionId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
