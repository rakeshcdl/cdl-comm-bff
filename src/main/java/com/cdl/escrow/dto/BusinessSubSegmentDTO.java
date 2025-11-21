package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessSubSegmentDTO implements Serializable {

    private Long id;

    private String UUID;

    private String subSegmentName;

    private String subSegmentDescription;

    private Boolean active;

    private BusinessSegmentDTO businessSegmentNameDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
