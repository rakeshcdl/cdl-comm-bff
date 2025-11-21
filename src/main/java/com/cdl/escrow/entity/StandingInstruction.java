package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "standing_instruction")
public class StandingInstruction implements Serializable {

    @Id
    @SequenceGenerator(
            name = "standing_instruction_id_seq_gen",
            sequenceName = "standing_instruction_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "standing_instruction_seq_gen"
    )
    private Long id;

    private String standingInstructionReferenceNumber;

    private String clientFullName;

    private BigDecimal debitAmountCap;

    private BigDecimal debitAmount;

    private BigDecimal minimumBalanceAmount;

    private BigDecimal thresholdAmount;

    private ZonedDateTime firstTransactionDateTime;

    private ZonedDateTime instructionExpiryDateTime;

    private Integer retryIntervalDays;

    private Boolean retryUntilMonthEndFlag;

    private String instructionRemarks;

    private ZonedDateTime nextExecutionDateTime;


    @OneToMany(mappedBy = "standingInstruction")
    @JsonIgnore
    private Set<ScheduledJob> scheduledJobs = new HashSet<>();

    @OneToMany(mappedBy = "standingInstruction")
    @JsonIgnore
    private Set<StandingInstructionBeneficiary> siPaymentBeneficiaries = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private EscrowAgreement dealNo;

    @ManyToOne
    private ApplicationSetting status;

    @ManyToOne
    private ApplicationSetting transferType;

    @ManyToOne
    private ApplicationSetting occurrence;

    @ManyToOne
    private ApplicationSetting recurringFrequency;

    @ManyToOne
    private ApplicationSetting holidaySetup;

    @ManyToOne
    private ApplicationSetting dependentScenario;

    @ManyToOne
    @JsonIgnore
    private EscrowAccount formAccountDr;

    @ManyToOne
    @JsonIgnore
    private StandingInstruction dependence;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
