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
public class AgreementSubTypeCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter subTypeName;

    private StringFilter subTypeDescription;

    private BooleanFilter active;

    private LongFilter agreementTypeId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
