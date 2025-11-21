package com.cdl.escrow.entity;

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
@Table(name = "agreement_segment")
public class AgreementSegment implements Serializable {

    // old name Deal Segment
    @Id
    @SequenceGenerator(
            name = "agreement_segment_id_seq_gen",
            sequenceName = "agreement_segment_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_segment_seq_gen"
    )
    private Long id;

    private String uuid;

    private String segmentName;

    private String segmentDescription;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
