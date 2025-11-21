package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "transaction_processing_history")
public class TransactionProcessingHistory implements Serializable {

    @Id
    @SequenceGenerator(
            name = "transaction_processing_history_id_seq_gen",
            sequenceName = "transaction_processing_history_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_processing_history_seq_gen"
    )
    private Long id;

    private Integer retryAttemptCount;
    private Long scheduledJobReferenceId;

    private ZonedDateTime createdDateTime;
    private ZonedDateTime lastUpdatedDateTime;

    private String utrReferenceNumber;
    private ZonedDateTime transactionDateTime;

    private BigDecimal creditedAmount;

    @Lob
    @Column(name = "transaction_response_payload")
    private String transactionResponsePayload;

    private String processingStatusCode;   // e.g., SUCCESS, FAILED, RETRYING
    private String initiatedBy;            // user/system that triggered transaction
    private String remarks;                // free-text notes or exception cause
    private String correlationId;          // traceability across systems


    @ManyToOne
    private ProcessingStatus status;

    @ManyToOne
    @JsonIgnore
    private StandingInstructionBeneficiary standingInstructionBeneficiary;

    private Boolean enabled ;

    private Boolean deleted ;
}
