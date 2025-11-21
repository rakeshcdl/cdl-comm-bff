package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "agreement_budget_plan")
public class AgreementBudgetPlan {

    @Id
    @SequenceGenerator(
            name = "agreement_budget_plan_id_seq_gen",
            sequenceName = "agreement_budget_plan_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_budget_plan_seq_gen"
    )
    private Long id;

    @Lob
    @Column(name = "expense_json")
    private String expenseMetadataJson;

    @ManyToOne
    @JsonIgnore
    private EscrowAgreement escrowAgreement;

    private Boolean enabled ;

    private Boolean deleted ;

}
