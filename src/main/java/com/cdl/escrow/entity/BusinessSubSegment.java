package com.cdl.escrow.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "business_sub_segment")
public class BusinessSubSegment implements Serializable {

    @Id
    @SequenceGenerator(
            name = "business_sub_segment_id_seq_gen",
            sequenceName = "business_sub_segment_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "business_sub_segment_seq_gen"
    )
    private Long id;

    private String UUID;

    private String subSegmentName;

    private String subSegmentDescription;

    private Boolean active;

    @ManyToOne
    @JsonIgnore
    private BusinessSegment businessSegmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;

}
