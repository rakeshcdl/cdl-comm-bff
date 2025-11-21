package com.cdl.escrow.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "business_calender")
public class BusinessCalendar implements Serializable {

    // old name holiday calender

    @Id
    @SequenceGenerator(
            name = "business_calender_id_seq_gen",
            sequenceName = "business_calender_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "business_calender_seq_gen"
    )
    private Long id;

    private String uuid;

    private String calendarName;

    private ZonedDateTime nonWorkingDate;

    private Boolean isEnabled;

    @ManyToOne
    private ApplicationSetting deductionToHappen;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
