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
public class ScheduledJobCriteria implements Serializable {


    private LongFilter id;

    private IntegerFilter executionSequenceNumber;

    private StringFilter scheduledJobIdentifier;

    private ZonedDateTimeFilter scheduledExecutionDateTime;

    private StringFilter jobStatusCode;         // e.g., PENDING, RUNNING, SUCCESS, FAILED

    private StringFilter triggeredBy;           // system/user ID that initiated the job

    private ZonedDateTimeFilter completedDateTime;

    private StringFilter remarks;               // free-text execution notes

    private LongFilter statusId;

    private LongFilter standingInstructionId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;

}
