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
public class BusinessCalendarCriteria implements Serializable {

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter calendarName;

    private ZonedDateTimeFilter nonWorkingDate;

    private BooleanFilter isEnabled;

    private LongFilter deductionToHappenId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
