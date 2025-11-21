package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledJobDTO implements Serializable {

    private Long id;

    private Integer executionSequenceNumber;

    private String scheduledJobIdentifier;

    private ZonedDateTime scheduledExecutionDateTime;

    private String jobStatusCode;         // e.g., PENDING, RUNNING, SUCCESS, FAILED

    private String triggeredBy;           // system/user ID that initiated the job

    private ZonedDateTime completedDateTime;

    private String remarks;               // free-text execution notes

    private ProcessingStatusDTO statusDTO;

    private StandingInstructionDTO standingInstructionDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
