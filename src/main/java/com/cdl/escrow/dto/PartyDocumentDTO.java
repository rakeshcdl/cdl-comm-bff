package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDocumentDTO implements Serializable {

    private Long id;

    private String uuid;

    private String documentName;

    private String documentDescription;

    private String documentTypeCode;

    private Boolean active;

    private BeneficiaryDTO beneficiaryDTO;

    private TaskStatusDTO taskStatusDTO;

    private PartyDTO partyDTO;

    private EscrowAgreementDTO escrowAgreementDTO;

    private PaymentInstructionDTO unitaryPaymentDTO;

    //private Set<AttachmentDTO> appDocumentsDTO = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
