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
public class StandingInstructionBeneficiaryCriteria implements Serializable {


    private LongFilter id;

    private StringFilter beneficiaryAccountNumber;

    private StringFilter beneficiaryBankIfscCode;

    private BigDecimalFilter creditAmountCap;

    private BigDecimalFilter creditAmount;

    private IntegerFilter transferPriorityLevel;

    private BigDecimalFilter creditSharePercentage;

    private StringFilter currencyCode;          // ISO 4217 currency code

    private StringFilter paymentModeCode;       // e.g., NEFT, RTGS, SWIFT, INTERNAL

    private LongFilter beneficiaryNameId;

    private LongFilter paymentModeId;

    private LongFilter currencyId;

    private LongFilter standingInstructionId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;

}
