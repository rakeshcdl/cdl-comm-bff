package com.cdl.escrow.dto;

import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.PartyDocument;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO implements Serializable {

    private Long id;

    private String name;

    private String location;

    private String module;

    private Long recordId;

    private String storageType;

    private ZonedDateTime uploadDate;

    private String documentSize;

    private ZonedDateTime validityDate;

    private String eventDetail;

    private PartyDocumentDTO partyDocumentDTO;

    private ApplicationSettingDTO documentTypeDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
