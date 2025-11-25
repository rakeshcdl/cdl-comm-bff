package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.DoubleFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BulkPaymentUploadCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private DoubleFilter paymentAmount;

    private StringFilter batchRemarks;

    private StringFilter utrReferenceNumber;

    private StringFilter transactionStatus;

    private StringFilter transactionRemarks;

    private DoubleFilter exchangeRate;

    private DoubleFilter convertedPaymentAmount;

    private StringFilter batchReferenceId;

    private StringFilter payerAddressLine1;

    private StringFilter payerAddressLine2;

    private StringFilter payerAddressLine3;

    private StringFilter transactionDateTime;

    private StringFilter paymentResponsePayload;

    private StringFilter sourceEntityType;

    private StringFilter documentName;

    private StringFilter recordValidationStatus;

    private StringFilter validationErrorMessage;

    private BooleanFilter active;

    private LongFilter partyId;

    private LongFilter escrowAgreementId;

    private LongFilter escrowAccountId;

    private LongFilter transactionTypeId;

    private LongFilter currencyId;

    private LongFilter taskStatusId;

    private LongFilter beneficiaryId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
