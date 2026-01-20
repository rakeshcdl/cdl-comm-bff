package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AgreementFeeScheduleCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter regulatoryRefNo;

    private ZonedDateTimeFilter effectiveStartDate;

    private ZonedDateTimeFilter effectiveEndDate;

    private StringFilter operatingLocation;

    private StringFilter priorityLevel;

    private StringFilter transactionRateAmount;

    private StringFilter debitAccountNumber;

    private StringFilter creditAccountNumber;

    private BooleanFilter active;

    private LongFilter feeId;

    private LongFilter feeTypeId;

    private LongFilter feesFrequencyId;

    private LongFilter frequencyBasisId;

    private LongFilter agreementTypeId;

    private LongFilter agreementSubTypeId;

    private LongFilter productProgramId;

    private LongFilter escrowAgreementId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
