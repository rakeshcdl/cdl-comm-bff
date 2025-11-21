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
public class StandingInstructionCriteria implements Serializable {

    private LongFilter id;

    private StringFilter standingInstructionReferenceNumber;

    private StringFilter clientFullName;

    private BigDecimalFilter debitAmountCap;

    private BigDecimalFilter debitAmount;

    private BigDecimalFilter minimumBalanceAmount;

    private BigDecimalFilter thresholdAmount;

    private ZonedDateTimeFilter firstTransactionDateTime;

    private ZonedDateTimeFilter instructionExpiryDateTime;

    private IntegerFilter retryIntervalDays;

    private BooleanFilter retryUntilMonthEndFlag;

    private StringFilter instructionRemarks;

    private ZonedDateTimeFilter nextExecutionDateTime;

    private LongFilter dealNoId;

    private LongFilter statusId;

    private LongFilter transferTypeId;

    private LongFilter occurrenceId;

    private LongFilter recurringFrequencyId;

    private LongFilter holidaySetupId;

    private LongFilter dependentScenarioId;

    private LongFilter formAccountDrId;

    private LongFilter dependenceId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;

}
