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
@Table(name = "agreement_signatory")
public class AgreementSignatory implements Serializable {

    @Id
    @SequenceGenerator(
            name = "agreement_signatory_id_seq_gen",
            sequenceName = "agreement_signatory_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_signatory_seq_gen"
    )
    private Long id;

    private String partyReferenceNumber;

    private String partyCustomerReferenceNumber;

    private String partyFullName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String signatoryRole;

    private String notificationContactName;

    private String notificationAddressLine1;

    private String notificationAddressLine2;

    private String notificationAddressLine3;

    private String notificationEmailAddress;

    private String associationType;

    private Boolean isEnabled;

    @ManyToOne
    @JsonIgnore
    private AuthorizedSignatory authorizedSignatory;

    @ManyToOne
    @JsonIgnore
    private Party party;

    @ManyToOne
    @JsonIgnore
    private EscrowAgreement escrowAgreement;

    private Boolean enabled ;

    private Boolean deleted ;
}
