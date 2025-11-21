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
@Table(name = "general_ledger_account")
public class GeneralLedgerAccount implements Serializable {

   // old name GeneralLedger
   @Id
   @SequenceGenerator(
           name = "general_ledger_account_id_seq_gen",
           sequenceName = "general_ledger_account_id_seq",
           allocationSize = 50
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "general_ledger_account_seq_gen"
   )
    private Long id;

    private String uuid;

    private String ledgerAccountNumber;

    private String branchIdentifierCode;

    private String ledgerAccountDescription;

    private String ledgerAccountTypeCode;   // e.g., ASSET, LIABILITY, INCOME, EXPENSE

    private Boolean active;               // lifecycle management flag

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    private Boolean enabled ;

    private Boolean deleted ;

}
