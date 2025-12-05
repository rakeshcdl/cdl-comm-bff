package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitaryPaymentInstructionDTO implements Serializable {

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

    private EscrowAgreementDTO dealNoDTO;

    private EscrowAccountDTO accountNoDTO;

    private ApplicationSettingDTO paymentTypeDTO;

    private ApplicationSettingDTO adhocPaymentDTO;

    private ApplicationSettingDTO purposeDTO;

    private ApplicationSettingDTO beneficiaryCurrencyDTO;


    private Boolean enabled ;

    private Boolean deleted ;
}
