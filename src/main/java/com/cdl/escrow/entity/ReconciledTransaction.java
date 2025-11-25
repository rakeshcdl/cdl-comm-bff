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
@Table(name = "reconciled_transaction")
public class ReconciledTransaction implements Serializable {


    @Id
    @SequenceGenerator(
            name = "reconciled_transaction_id_seq_gen",
            sequenceName = "reconciled_transaction_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reconciled_transaction_seq_gen"
    )
    private Long id;

    private String reconTransactionId;

    private BigDecimal transactionAmount;

    private BigDecimal totalTransactionAmount;

    private ZonedDateTime transactionDateTime;

    private String transactionNarration;

    private String transactionDescription;

    private String processingRemarks;

    private Boolean allocatedFlag;

    private String transactionParticular1;

    private String transactionParticular2;

    private String transactionParticularRemark1;

    private String transactionParticularRemark2;

    private String chequeReferenceNumber;

    private Boolean tasUpdateRequestedFlag;

    private Boolean tasUpdateAppliedFlag;

    private Boolean tasUpdateEnabledFlag; // optional: use only if this is a config toggle

    private String unitReferenceNumber;

    private String tasPaymentStatusCode;

    private String batchTransactionId;

    @Lob
    @Column(name = "reconciliation_response")
    private String reconciliationResponsePayload;

    private Boolean rollbackFlag;

    @Lob
    @Column(name = "core_banking_response")
    private String coreBankingResponsePayload;

    private String paymentReferenceNumber;

    private String subBucketIdentifier;

    @ManyToOne
    @JsonIgnore
    private EscrowAgreement escrowAgreement;

    @ManyToOne
    private ApplicationSetting bucketType;

    @ManyToOne
    private ApplicationSetting subBucketType;

    @ManyToOne
    private ApplicationSetting depositMode;

    @ManyToOne
    @JsonIgnore
    private EscrowAccount escrowAccount;

    @ManyToOne
    @JsonIgnore
    private UnreconciledTransaction nonReconTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
