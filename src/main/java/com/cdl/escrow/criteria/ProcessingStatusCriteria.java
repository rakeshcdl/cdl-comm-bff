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
public class ProcessingStatusCriteria implements Serializable {

    private LongFilter id;

    private LongFilter previousJobReferenceId;

    private LongFilter currentJobReferenceId;

    private LongFilter nextJobReferenceId;

    private StringFilter processDetails;

    private StringFilter statusMessage;

    private ZonedDateTimeFilter createdDateTime;

    private LongFilter statusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
