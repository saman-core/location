package io.samancore.entity;

import io.samancore.entity.common.AuditCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "parish")
public class ParishEntity extends AuditCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_parish")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "municipality_id", nullable = false)
    private MunicipalityEntity municipality;

    @Column(length = 2)
    private String codeIne;
}
