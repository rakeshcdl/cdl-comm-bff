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
public class PartyDocumentCriteria implements Serializable {
    private LongFilter id;

    private StringFilter uuid;

    private StringFilter documentName;

    private StringFilter documentDescription;

    private StringFilter documentTypeCode;

    private BooleanFilter active;

    private LongFilter beneficiaryId;

    private LongFilter taskStatusId;

    private LongFilter partyId;

    private LongFilter escrowAgreementId;

    private LongFilter unitaryPaymentId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
