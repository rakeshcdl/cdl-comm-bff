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
@Table(name = "agreement_sub_type")
public class AgreementSubType implements Serializable {

    // old name Deal sub Type
    @Id
    @SequenceGenerator(
            name = "agreement_sub_type_id_seq_gen",
            sequenceName = "agreement_sub_type_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_sub_type_seq_gen"
    )
    private Long id;

    private String UUID;

    private String subTypeName;

    private String subTypeDescription;

    private Boolean active;

    @ManyToOne
    @JsonIgnore
    private AgreementType agreementType;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;
}
