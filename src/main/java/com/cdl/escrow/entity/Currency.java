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
@Table(name = "currency")
public class Currency implements Serializable {

    @Id
    @SequenceGenerator(
            name = "currency_id_seq_gen",
            sequenceName = "currency_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_seq_gen"
    )
    private Long id;

    private String uuid;

    private String description;

    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
