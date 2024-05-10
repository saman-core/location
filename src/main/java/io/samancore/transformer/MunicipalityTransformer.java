package io.samancore.transformer;

import io.samancore.entity.MunicipalityEntity;
import io.samancore.model.Municipality;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MunicipalityTransformer {

    @Inject
    Logger log;

    public Municipality toModel(MunicipalityEntity entity) {
        return Municipality.newBuilder().setId(entity.getId())
                .setId(entity.getId())
                .setLabel(entity.getName())
                .setParentId(entity.getState().getId())
                .build();
    }
}