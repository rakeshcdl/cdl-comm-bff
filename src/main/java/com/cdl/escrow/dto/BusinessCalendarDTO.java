package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCalendarDTO implements Serializable {

    private Long id;

    private String uuid;

    private String calendarName;

    private ZonedDateTime nonWorkingDate;

    private Boolean isEnabled;

    private ApplicationSettingDTO deductionToHappenDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
