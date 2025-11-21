package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
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
@Table(name = "escrow_agreement")
public class EscrowAgreement implements Serializable {

    // old name Deal

    @Id
    @SequenceGenerator(
            name = "escrow_agreement_id_seq_gen",
            sequenceName = "escrow_agreement_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "escrow_agreement_seq_gen"
    )
    private Long id;

    private String UUID;

    private String primaryEscrowCifNumber;

    private String productManagerName;

    private String relationshipManagerName;

    private String operatingLocationCode;

    private String customField1;

    private String customField2;

    private String customField3;

    private String customField4;

    private Boolean active;

    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnore
    private AgreementParameters agreementParameter;


    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnore
    private AgreementFeeSchedule agreementFee;

    @OneToMany(mappedBy = "escrowAgreement")
    @JsonIgnore
    private Set<EscrowAccount> escrowAccounts = new HashSet<>();

    @OneToMany(mappedBy = "escrowAgreement")
    @JsonIgnore
    private Set<AgreementSignatory> dealSignatories = new HashSet<>();

    @OneToMany(mappedBy = "escrowAgreement")
    @JsonIgnore
    private Set<PartyDocument> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private Party clientName;

    @ManyToOne
    @JsonIgnore
    private BusinessSegment businessSegment;

    @ManyToOne
    @JsonIgnore
    private BusinessSubSegment businessSubSegment;

    @ManyToOne
    private ApplicationSetting dealStatus;

    @ManyToOne
    private ApplicationSetting fees;

    @ManyToOne
    @JsonIgnore
    private AgreementType dealType;

    @ManyToOne
    @JsonIgnore
    private AgreementSubType dealSubType;

    @ManyToOne
    @JsonIgnore
    private ProductProgram productProgram;

    @ManyToOne
    private ApplicationSetting dealPriority;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "escrowAgreement")
    @JsonIgnore
    private Set<ReconciledTransaction> reconTransactions = new HashSet<>();

    @OneToMany(mappedBy = "escrowAgreement")
    @JsonIgnore
    private Set<UnreconciledTransaction> nonReconTransactions = new HashSet<>();

    @OneToMany(mappedBy = "escrowAgreement")
    @JsonIgnore
    private Set<BulkPaymentUpload> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;

}
