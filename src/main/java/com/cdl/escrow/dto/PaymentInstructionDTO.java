package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInstructionDTO implements Serializable {

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

    private EscrowAgreementDTO dealNoDTO;

    private EscrowAccountDTO accountNoDTO;

    private Set<PartyDocumentDTO> documentsDTO = new HashSet<>();

    private ApplicationSettingDTO paymentTypeDTO;

    private ApplicationSettingDTO adhocPaymentDTO;

    private ApplicationSettingDTO purposeDTO;

    private ApplicationSettingDTO beneficiaryCurrencyDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
