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
@Table(name = "payment_instruction")
public class PaymentInstruction implements Serializable {

    @Id
    @SequenceGenerator(
            name = "payment_instruction_id_seq_gen",
            sequenceName = "payment_instruction_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_instruction_seq_gen"
    )
    private Long id;

    private String paymentReferenceNumber;

    private String payerCustomerId;

    private String payerFullName;

    private String beneficiaryId;

    private String beneficiaryFullName;

    private String beneficiaryBankName;

    private String beneficiaryBankIfscCode;

    private String beneficiaryBankBic;

    private String beneficiaryAccountNumber;

    private String accountBalanceAmount;      // consider Double/BigDecimal if possible

    private Double paymentAmount;

    private String transactionRemarks;

    private ZonedDateTime paymentInitiationDateTime;

    private ZonedDateTime valueDateTime;

    private String additionalRemarks;

    private String utrReferenceNumber;

    private String paymentGatewayResponsePayload;

    private String coreBankingResponsePayload;

    private String transactionDateTime;       // consider ZonedDateTime for consistency

    private String transactionStatus;

    private Boolean tasPaymentFlag;

    private Boolean active;


    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnore
    private EscrowAgreement dealNo;

    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnore
    private EscrowAccount accountNo;

    @OneToMany(mappedBy = "unitaryPayment")
    @JsonIgnore
    private Set<PartyDocument> documents = new HashSet<>();

    @ManyToOne
    private ApplicationSetting paymentType;

    @ManyToOne
    private ApplicationSetting adhocPayment;

    @ManyToOne
    private ApplicationSetting purpose;

    @ManyToOne
    private ApplicationSetting beneficiaryCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
