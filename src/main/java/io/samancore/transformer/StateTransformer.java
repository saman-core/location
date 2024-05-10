package io.samancore.transformer;

import io.samancore.entity.StateEntity;
import io.samancore.model.State;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class StateTransformer {

    @Inject
    Logger log;

    public State toModel(StateEntity entity) {
        return State.newBuilder().setId(entity.getId())
                .setId(entity.getId())
                .setLabel(entity.getName())
                .build();
    }
}