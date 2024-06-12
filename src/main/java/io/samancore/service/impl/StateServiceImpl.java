package io.samancore.service.impl;

import io.samancore.model.PageData;
import io.samancore.model.State;
import io.samancore.repository.StateRepository;
import io.samancore.service.StateService;
import io.samancore.transformer.StateTransformer;
import io.samancore.utils.page.PageRequest;
import io.samancore.utils.page.PageUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class StateServiceImpl implements StateService {

    @Inject
    Logger log;

    @Inject
    StateRepository repository;

    @Inject
    StateTransformer transformer;

    @Override
    public PageData<State> getPageByLabel(String label, PageRequest pageRequest) {
        log.debugf("StateServiceImpl.getPageByLabel %s", label);
        var states = repository.getPageByLabel(label, pageRequest);
        return PageUtil.toPageModel(states, transformer::toModel);
    }

    @Override
    public State getById(Long id) {
        log.debugf("StateServiceImpl.getById %d", id);
        var entity = repository.getById(id);
        return transformer.toModel(entity);
    }
}
