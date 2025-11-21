package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
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
@Table(name = "agreement_parameter")
public class AgreementParameters implements Serializable {

    @Id
    @SequenceGenerator(
            name = "agreement_parameter_id_seq_gen",
            sequenceName = "agreement_parameter_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_parameter_seq_gen"
    )
    private Long id;

    private String UUID;

    private ZonedDateTime agreementEffectiveDate;

    private ZonedDateTime agreementExpiryDate;

    private String agreementRemarks;

    private Boolean active;

    @ManyToOne
    private ApplicationSetting permittedInvestmentAllowed;


    @ManyToOne
    private ApplicationSetting amendmentAllowed;

    @ManyToOne
    private ApplicationSetting dealClosureBasis;


    @OneToOne(mappedBy = "agreementParameter")
    @JsonIgnore
    private EscrowAgreement escrowAgreement;

    private Boolean enabled ;

    private Boolean deleted ;
}
