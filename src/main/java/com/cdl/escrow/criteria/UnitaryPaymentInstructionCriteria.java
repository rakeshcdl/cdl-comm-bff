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
public class UnitaryPaymentInstructionCriteria implements Serializable {

    private LongFilter id;

    private StringFilter paymentRefNo;

    private StringFilter customerId;

    private StringFilter customerName;

    private StringFilter beneficiaryId;

    private StringFilter beneficiaryName;

    private StringFilter bankName;

    private StringFilter ifscCode;

    private StringFilter bicCode;

    private StringFilter beneficiaryAccountNo;

    private StringFilter accountBalance;

    private BigDecimalFilter paymentAmount;

    private StringFilter otherRemarks;

    private ZonedDateTimeFilter paymentDate;

    private ZonedDateTimeFilter valueDate;

    private StringFilter remarks;

    private StringFilter utrReferenceNumber;

    private StringFilter paymentResponseObj;

    private StringFilter responseObject;

    private StringFilter transactionDate;

    private StringFilter paymentStatus;

    private BooleanFilter isTasPayment;

    private BooleanFilter isEnabled;

    private LongFilter dealNoId;

    private LongFilter accountNoId;

    private LongFilter paymentTypeId;

    private LongFilter adhocPaymentId;

    private LongFilter purposeId;

    private LongFilter beneficiaryCurrencyId;


    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
