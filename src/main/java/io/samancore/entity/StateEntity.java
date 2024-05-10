package io.samancore.entity;

import io.samancore.entity.common.AuditCommon;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "state")
public class StateEntity extends AuditCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_state")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 2)
    private String codeIne;
}
