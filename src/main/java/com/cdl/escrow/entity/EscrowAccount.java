package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "escrow_account")
public class EscrowAccount implements Serializable {
    @Id
    @SequenceGenerator(
            name = "escrow_account_id_seq_gen",
            sequenceName = "escrow_account_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "escrow_account_seq_gen"
    )
    private Long id;

    private String UUID;

    private String accountNumber;

    private String productCode;           // or productCodeIdentifier

    private String accountDisplayName;

    private String ibanNumber;

    private String officialAccountTitle;

    private String virtualAccountNumber;

    private String accountTypeCode;

    private String assignmentStatus;      // or Boolean assignedFlag

    private String assignedToReference;

    private ZonedDateTime accountOpenDateTime;

    private String referenceField1;

    private String referenceField2;

    private Boolean active;

    @ManyToOne
    private ApplicationSetting taxPayment;

    @ManyToOne
    private ApplicationSetting currency;

    @ManyToOne
    @JsonIgnore
    private AccountPurpose accountPurpose;

    @ManyToOne
    private ApplicationSetting accountCategory;

    @ManyToOne
    private ApplicationSetting primaryAccount;

    @ManyToOne
    private ApplicationSetting bulkUploadProcessing;

    @ManyToOne
    private ApplicationSetting unitaryPayment;

    @ManyToOne
   @JsonIgnore
    private AccountType accountType;

    @ManyToOne
   @JsonIgnore
    private AccountTypeCategory accountTypeCategory;

    @ManyToOne
   @JsonIgnore
    private EscrowAgreement escrowAgreement;

    @OneToMany(mappedBy = "escrowAccount")
    @JsonIgnore
    private Set<ReconciledTransaction> reconTransactions = new HashSet<>();

    @OneToMany(mappedBy = "escrowAccount")
    @JsonIgnore
    private Set<UnreconciledTransaction> nonReconTransactions = new HashSet<>();

    @OneToMany(mappedBy = "escrowAccount")
    @JsonIgnore
    private Set<BulkPaymentUpload> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;

}
