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
@Table(name = "agreement_type")
public class AgreementType implements Serializable {

    // old name Deal Type
    @Id
    @SequenceGenerator(
            name = "agreement_type_id_seq_gen",
            sequenceName = "agreement_type_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_type_seq_gen"
    )
    private Long id;

    private String uuid;

    private String agreementTypeName;

    private String agreementTypeDescription;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
