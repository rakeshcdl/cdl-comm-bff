package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionProcessingHistoryDTO implements Serializable {

    private Long id;

    private Integer retryAttemptCount;

    private Long scheduledJobReferenceId;

    private ZonedDateTime createdDateTime;

    private ZonedDateTime lastUpdatedDateTime;

    private String utrReferenceNumber;

    private ZonedDateTime transactionDateTime;

    private BigDecimal creditedAmount;

    private String transactionResponsePayload;

    private String processingStatusCode;   // e.g., SUCCESS, FAILED, RETRYING

    private String initiatedBy;            // user/system that triggered transaction

    private String remarks;                // free-text notes or exception cause

    private String correlationId;          // traceability across systems

    private ProcessingStatusDTO statusDTO;

    private StandingInstructionBeneficiaryDTO standingInstructionBeneficiaryDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
