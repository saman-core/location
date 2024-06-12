package io.samancore.entity;

import io.samancore.entity.common.AuditCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "municipality")
public class MunicipalityEntity extends AuditCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_municipality")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;

    @Column(length = 2)
    private String codeIne;
}
