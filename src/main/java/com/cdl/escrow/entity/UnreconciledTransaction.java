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
@Table(name = "unreconciled_transaction")
public class UnreconciledTransaction implements Serializable {

    @Id
    @SequenceGenerator(
            name = "unreconciled_transaction_id_seq_gen",
            sequenceName = "unreconciled_transaction_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "unreconciled_transaction_seq_gen"
    )
    private Long id;

    private String unReconTransactionId;
    private String transactionReferenceNumber;

    private BigDecimal transactionAmount;
    private BigDecimal totalTransactionAmount;

    private ZonedDateTime transactionDateTime;

    private String transactionNarration;
    private String transactionDescription;

    private Boolean discardFlag;
    private Boolean allocatedFlag;

    private String transactionParticular1;
    private String transactionParticular2;
    private String transactionParticularRemark1;
    private String transactionParticularRemark2;

    private String chequeReferenceNumber;

    private Boolean tasUpdateRequestedFlag;
    private Boolean tasUpdateAppliedFlag;

    private ZonedDateTime valueDateTime;
    private ZonedDateTime postedDateTime;
    private ZonedDateTime processingDateTime;

    private String branchIdentifierCode;
    private String postedBranchIdentifierCode;

    private String currencyCode;

    private String customField1;
    private String customField2;
    private String customField3;
    private String customField4;
    private String customField5;

    private String primaryUnitHolderFullName;

    private Boolean unallocatedCategoryFlag;

    private String tasPaymentStatusCode;

    private ZonedDateTime discardedDateTime;

    private Boolean creditedToEscrowFlag;

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
    @JsonIgnore
    private EscrowAccount escrowAccount;

    @ManyToOne
    private ApplicationSetting depositMode;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
