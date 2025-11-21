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
public class PaymentInstructionCriteria implements Serializable {

    private LongFilter id;

    private StringFilter paymentReferenceNumber;

    private StringFilter payerCustomerId;

    private StringFilter payerFullName;

    private StringFilter beneficiaryId;

    private StringFilter beneficiaryFullName;

    private StringFilter beneficiaryBankName;

    private StringFilter beneficiaryBankIfscCode;

    private StringFilter beneficiaryBankBic;

    private StringFilter beneficiaryAccountNumber;

    private StringFilter accountBalanceAmount;      // consider Double/BigDecimal if possible

    private DoubleFilter paymentAmount;

    private StringFilter transactionRemarks;

    private ZonedDateTimeFilter paymentInitiationDateTime;

    private ZonedDateTimeFilter valueDateTime;

    private StringFilter additionalRemarks;

    private StringFilter utrReferenceNumber;

    private StringFilter paymentGatewayResponsePayload;

    private StringFilter coreBankingResponsePayload;

    private StringFilter transactionDateTime;       // consider ZonedDateTime for consistency

    private StringFilter transactionStatus;

    private BooleanFilter tasPaymentFlag;

    private BooleanFilter active;

    private LongFilter dealNoId;

    private LongFilter accountNoId;

    private LongFilter paymentTypeId;

    private LongFilter adhocPaymentId;

    private LongFilter purposeId;

    private LongFilter beneficiaryCurrencyId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
