package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementSignatoryDTO implements Serializable {

    private Long id;

    private String partyReferenceNumber;

    private String partyCustomerReferenceNumber;

    private String partyFullName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String signatoryRole;

    private String notificationContactName;

    private String notificationAddressLine1;

    private String notificationAddressLine2;

    private String notificationAddressLine3;

    private String notificationEmailAddress;

    private String associationType;

    private Boolean isEnabled;

    private AuthorizedSignatoryDTO authorizedSignatoryDTO;

    private PartyDTO partyDTO;

    private EscrowAgreementDTO escrowAgreementDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
