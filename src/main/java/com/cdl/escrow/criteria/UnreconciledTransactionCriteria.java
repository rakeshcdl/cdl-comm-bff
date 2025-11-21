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
public class UnreconciledTransactionCriteria implements Serializable {

    private LongFilter id;

    private StringFilter unReconTransactionId;
    private StringFilter transactionReferenceNumber;

    private BigDecimalFilter transactionAmount;
    private BigDecimalFilter totalTransactionAmount;

    private ZonedDateTimeFilter transactionDateTime;

    private StringFilter transactionNarration;
    private StringFilter transactionDescription;

    private BooleanFilter discardFlag;
    private BooleanFilter allocatedFlag;

    private StringFilter transactionParticular1;
    private StringFilter transactionParticular2;
    private StringFilter transactionParticularRemark1;
    private StringFilter transactionParticularRemark2;

    private StringFilter chequeReferenceNumber;

    private BooleanFilter tasUpdateRequestedFlag;
    private BooleanFilter tasUpdateAppliedFlag;

    private ZonedDateTimeFilter valueDateTime;
    private ZonedDateTimeFilter postedDateTime;
    private ZonedDateTimeFilter processingDateTime;

    private StringFilter branchIdentifierCode;
    private StringFilter postedBranchIdentifierCode;

    private StringFilter currencyCode;

    private StringFilter customField1;
    private StringFilter customField2;
    private StringFilter customField3;
    private StringFilter customField4;
    private StringFilter customField5;

    private StringFilter primaryUnitHolderFullName;

    private BooleanFilter unallocatedCategoryFlag;

    private StringFilter tasPaymentStatusCode;

    private ZonedDateTimeFilter discardedDateTime;

    private BooleanFilter creditedToEscrowFlag;

    private StringFilter coreBankingResponsePayload;

    private StringFilter paymentReferenceNumber;
    private StringFilter subBucketIdentifier;

    private LongFilter escrowAgreementId;

    private LongFilter bucketTypeId;

    private LongFilter subBucketTypeId;

    private LongFilter escrowAccountId;

    private LongFilter depositModeId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled;

    private BooleanFilter deleted;
}
