package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "standing_instruction_beneficiary")
public class StandingInstructionBeneficiary implements Serializable {

    @Id
    @SequenceGenerator(
            name = "standing_instruction_beneficiary_id_seq_gen",
            sequenceName = "standing_instruction_beneficiary_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "standing_instruction_beneficiary_seq_gen"
    )
    private Long id;

    private String beneficiaryAccountNumber;

    private String beneficiaryBankIfscCode;

    private BigDecimal creditAmountCap;

    private BigDecimal creditAmount;

    private Integer transferPriorityLevel;

    private BigDecimal creditSharePercentage;

    private String currencyCode;          // ISO 4217 currency code

    private String paymentModeCode;       // e.g., NEFT, RTGS, SWIFT, INTERNAL


    @OneToMany(mappedBy = "standingInstructionBeneficiary")
    @JsonIgnore
    private Set<TransactionProcessingHistory> histories = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private Beneficiary beneficiaryName;

    @ManyToOne
    private ApplicationSetting paymentMode;

    @ManyToOne
    private ApplicationSetting currency;

    @ManyToOne
    @JsonIgnore
    private StandingInstruction standingInstruction;

    private Boolean enabled ;

    private Boolean deleted ;

}
