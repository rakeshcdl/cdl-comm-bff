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
@Table(name = "party")
public class Party implements Serializable {
// This is customer

    @Id
    @SequenceGenerator(
            name = "party_id_seq_gen",
            sequenceName = "party_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "party_seq_gen"
    )
    private Long id;

    private String UUID;

    private String partyCifNumber;
    private String partyFullName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String telephoneNumber;
    private String mobileNumber;
    private String emailAddress;
    private String bankIdentifier;
    private String passportIdentificationDetails;
    private String backupProjectAccountOwnerName;
    private String projectAccountOwnerName;
    private String assistantRelationshipManagerName;
    private String teamLeaderName;
    private String additionalRemarks;
    private String relationshipManagerName;
    private Boolean active;


    @OneToMany(mappedBy = "party")
    @JsonIgnore
    private Set<AuthorizedSignatory> signatories = new HashSet<>();

    @OneToMany(mappedBy = "party")
    @JsonIgnore
    private Set<PartyDocument> documents = new HashSet<>();

    @ManyToOne
    private ApplicationSetting partyConstituent;

    @ManyToOne
    private ApplicationSetting role;


    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "party")
    @JsonIgnore
    private Set<AgreementSignatory> agreementSignatories = new HashSet<>();

    @OneToMany(mappedBy = "party")
    @JsonIgnore
    private Set<BulkPaymentUpload> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
