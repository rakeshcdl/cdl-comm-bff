package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "bulk_payment_upload")
public class BulkPaymentUpload implements Serializable {

    @Id
    @SequenceGenerator(
            name = "bulk_payment_upload_id_seq_gen",
            sequenceName = "bulk_payment_upload_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bulk_payment_upload_seq_gen"
    )
    private Long id;

    private String UUID;

    private Double paymentAmount;

    private String batchRemarks;

    private String utrReferenceNumber;

    private String transactionStatus;

    private String transactionRemarks;

    private Double exchangeRate;

    private Double convertedPaymentAmount;

    private String batchReferenceId;

    private String payerAddressLine1;

    private String payerAddressLine2;

    private String payerAddressLine3;

    private String transactionDateTime;

    private String paymentResponsePayload;

    private String sourceEntityType;

    private String documentName;

    private String recordValidationStatus;

    private String validationErrorMessage;

    private Boolean active;

    @ManyToOne
    @JsonIgnore
    private Party party;

    @ManyToOne
    @JsonIgnore
    private EscrowAgreement escrowAgreement;

    @ManyToOne
    @JsonIgnore
    private EscrowAccount escrowAccount;

    @ManyToOne
    private ApplicationSetting transactionType;

    @ManyToOne
    private ApplicationSetting currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @ManyToOne
    @JsonIgnore
    private Beneficiary beneficiary;

    private Boolean enabled ;

    private Boolean deleted ;
}
