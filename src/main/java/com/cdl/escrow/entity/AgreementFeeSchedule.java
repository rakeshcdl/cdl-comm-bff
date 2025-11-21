package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "agreement_fee_schedule")
public class AgreementFeeSchedule {
    @Id
    @SequenceGenerator(
            name = "agreement_fee_schedule_id_seq_gen",
            sequenceName = "agreement_fee_schedule_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_fee_schedule_seq_gen"
    )
    private Long id;

    private String UUID;

    private ZonedDateTime effectiveStartDate;

    private ZonedDateTime effectiveEndDate;

    private String operatingLocation;

    private String priorityLevel;

    private String transactionRateAmount;

    private String debitAccountNumber;

    private String creditAccountNumber;

    private Boolean active;

    @ManyToOne
    private ApplicationSetting fee;

    @ManyToOne
    private ApplicationSetting feeType;

    @ManyToOne
    private ApplicationSetting feesFrequency;

    @ManyToOne
    private ApplicationSetting frequencyBasis;

    @ManyToOne
    @JsonIgnore
    private AgreementType agreementType;

    @ManyToOne
    @JsonIgnore
    private AgreementSubType agreementSubType;

    @ManyToOne
    @JsonIgnore
    private ProductProgram productProgram;

    @JsonIgnore
    @OneToOne(mappedBy = "agreementFee")
    private EscrowAgreement escrowAgreement;

    private Boolean enabled ;

    private Boolean deleted ;

}
