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
public class TransactionProcessingHistoryCriteria implements Serializable {

    private LongFilter id;

    private IntegerFilter retryAttemptCount;

    private LongFilter scheduledJobReferenceId;

    private ZonedDateTimeFilter createdDateTime;

    private ZonedDateTimeFilter lastUpdatedDateTime;

    private StringFilter utrReferenceNumber;

    private ZonedDateTimeFilter transactionDateTime;

    private BigDecimalFilter creditedAmount;

    private StringFilter transactionResponsePayload;

    private StringFilter processingStatusCode;   // e.g., SUCCESS, FAILED, RETRYING

    private StringFilter initiatedBy;            // user/system that triggered transaction

    private StringFilter remarks;                // free-text notes or exception cause

    private StringFilter correlationId;          // traceability across systems

    private LongFilter statusId;

    private LongFilter standingInstructionBeneficiaryId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
