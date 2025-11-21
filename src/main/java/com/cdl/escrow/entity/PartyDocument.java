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
@Table(name = "party_document")
public class PartyDocument implements Serializable {

    @Id
    @SequenceGenerator(
            name = "party_document_id_seq_gen",
            sequenceName = "party_document_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "party_document_seq_gen"
    )
    private Long id;

    private String uuid;

    private String documentName;
    private String documentDescription;
    private String documentTypeCode;
    private Boolean active;

    @ManyToOne
    @JsonIgnore
    private Beneficiary beneficiary;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @ManyToOne
    @JsonIgnore
    private Party party;

    @ManyToOne
    @JsonIgnore
    private EscrowAgreement escrowAgreement;

    @ManyToOne
    @JsonIgnore
    private PaymentInstruction unitaryPayment;

    @OneToMany(mappedBy = "partyDocument")
    @JsonIgnore
    private Set<Attachment> appDocuments = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;

}
