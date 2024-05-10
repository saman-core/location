package io.samancore.transformer;

import io.samancore.entity.ParishEntity;
import io.samancore.model.Parish;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ParishTransformer {

    @Inject
    Logger log;

    public Parish toModel(ParishEntity entity) {
        return Parish.newBuilder().setId(entity.getId())
                .setId(entity.getId())
                .setLabel(entity.getName())
                .setParentId(entity.getMunicipality().getId())
                .build();
    }
}