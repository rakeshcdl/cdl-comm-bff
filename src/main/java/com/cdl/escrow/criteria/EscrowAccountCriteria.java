package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EscrowAccountCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter accountNumber;

    private StringFilter productCode;           // or productCodeIdentifier

    private StringFilter accountDisplayName;

    private StringFilter ibanNumber;

    private StringFilter officialAccountTitle;

    private StringFilter virtualAccountNumber;

    private StringFilter accountTypeCode;

    private StringFilter assignmentStatus;      // or Boolean assignedFlag

    private StringFilter assignedToReference;

    private ZonedDateTimeFilter accountOpenDateTime;

    private StringFilter referenceField1;

    private StringFilter referenceField2;

    private BooleanFilter active;

    private LongFilter taxPaymentId;

    private LongFilter currencyId;

    private LongFilter accountPurposeId;

    private LongFilter accountCategoryId;

    private LongFilter primaryAccountId;

    private LongFilter bulkUploadProcessingId;

    private LongFilter unitaryPaymentId;

    private LongFilter accountTypeId;

    private LongFilter accountTypeCategoryId;

    private LongFilter escrowAgreementId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
