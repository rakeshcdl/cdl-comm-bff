package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscrowAgreementDTO implements Serializable {

    private Long id;

    private String UUID;

    private String primaryEscrowCifNumber;

    private String productManagerName;

    private String relationshipManagerName;

    private String operatingLocationCode;

    private String customField1;

    private String customField2;

    private String customField3;

    private String customField4;

    private Boolean active;

    private AgreementParametersDTO agreementParametersDTO;

    private AgreementFeeScheduleDTO agreementFeeDTO;

   // private Set<EscrowAccountDTO> escrowAccounts = new HashSet<>();

   // private Set<AgreementSignatoryDTO> dealSignatories = new HashSet<>();

   // private Set<PartyDocumentDTO> documents = new HashSet<>();

    private PartyDTO clientNameDTO;

    private BusinessSegmentDTO businessSegmentDTO;

    private BusinessSubSegmentDTO businessSubSegmentDTO;

    private ApplicationSettingDTO dealStatusDTO;

    private ApplicationSettingDTO feesDTO;

    private AgreementTypeDTO dealTypeDTO;

    private AgreementSubTypeDTO dealSubTypeDTO;

    private ProductProgramDTO productProgramDTO;

    private ApplicationSettingDTO dealPriorityDTO;

    private TaskStatusDTO taskStatusDTO;

   // private Set<ReconciledTransactionDTO> reconTransactions = new HashSet<>();

   // private Set<UnreconciledTransactionDTO> nonReconTransactions = new HashSet<>();

   // private Set<BulkPaymentUploadDTO> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
