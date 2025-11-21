package com.cdl.escrow.criteria;

import com.cdl.escrow.dto.ApplicationSettingDTO;
import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AttachmentCriteria implements Serializable {

    private LongFilter id;

    private StringFilter name;

    private StringFilter location;

    private StringFilter module;

    private LongFilter recordId;

    private StringFilter storageType;

    private ZonedDateTimeFilter uploadDate;

    private StringFilter documentSize;

    private ZonedDateTimeFilter validityDate;

    private StringFilter eventDetail;

    private LongFilter partyDocumentId;

    private LongFilter documentTypeId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
