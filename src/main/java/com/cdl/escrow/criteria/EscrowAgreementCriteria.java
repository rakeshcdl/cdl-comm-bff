package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EscrowAgreementCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter primaryEscrowCifNumber;

    private StringFilter productManagerName;

    private StringFilter relationshipManagerName;

    private StringFilter operatingLocationCode;

    private StringFilter customField1;

    private StringFilter customField2;

    private StringFilter customField3;

    private StringFilter customField4;

    private BooleanFilter active;

    private LongFilter agreementParametersId;

    private LongFilter agreementFeeId;

    private LongFilter clientNameId;

    private LongFilter businessSegmentId;

    private LongFilter businessSubSegmentId;

    private LongFilter dealStatusId;

    private LongFilter feesId;

    private LongFilter dealTypeId;

    private LongFilter dealSubTypeId;

    private LongFilter productProgramId;

    private LongFilter dealPriorityId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
