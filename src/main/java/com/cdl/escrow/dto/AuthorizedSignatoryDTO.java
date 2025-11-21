package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedSignatoryDTO implements Serializable {

    private Long id;

    private String customerCifNumber;

    private String signatoryFullName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String telephoneNumber;

    private String mobileNumber;

    private String emailAddress;

    private String notificationContactName;

    private String signatoryCifNumber;

    private String notificationEmailAddress;

    private byte[] notificationSignatureFile;

    private String notificationSignatureMimeType;

    private Boolean active;

    private ApplicationSettingDTO cifExistsDTO;

    private PartyDTO partyDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
