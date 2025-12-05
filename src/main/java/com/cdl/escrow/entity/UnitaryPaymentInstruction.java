package com.cdl.escrow.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
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
@Table(name = "unitary_payment_instruction")
public class UnitaryPaymentInstruction {

    @Id
    @SequenceGenerator(
            name = "unitary_payment_instruction_id_seq_gen",
            sequenceName = "unitary_payment_instruction_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "unitary_payment_instruction_seq_gen"
    )
    private Long id;

    private String paymentRefNo;

    private String customerId;

    private String customerName;

    private String beneficiaryId;

    private String beneficiaryName;

    private String bankName;

    private String ifscCode;

    private String bicCode;

    private String beneficiaryAccountNo;

    private String accountBalance;

    private BigDecimal paymentAmount;

    private String otherRemarks;

    private ZonedDateTime paymentDate;

    private ZonedDateTime valueDate;

    private String remarks;

    private String utrReferenceNumber;

    private String paymentResponseObj;

    private String responseObject;

    private String transactionDate;

    private String paymentStatus;

    private Boolean isTasPayment;

    private Boolean isEnabled;

    @OneToOne
    @JoinColumn(unique = true)
    private EscrowAgreement dealNo;

    @OneToOne
    @JoinColumn(unique = true)
    private EscrowAccount accountNo;

   /* @OneToMany(mappedBy = "unitaryPayment")
    @JsonIgnore
    private Set<PartyDocument> documents = new HashSet<>();
*/
    @ManyToOne
    private ApplicationSetting paymentType;

    @ManyToOne
    private ApplicationSetting adhocPayment;

    @ManyToOne
    private ApplicationSetting purpose;

    @ManyToOne
    private ApplicationSetting beneficiaryCurrency;


    private Boolean enabled ;

    private Boolean deleted ;
}
