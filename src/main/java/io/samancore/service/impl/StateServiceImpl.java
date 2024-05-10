package io.samancore.service.impl;

import io.samancore.service.StateService;
import io.samancore.transformer.StateTransformer;
import io.samancore.repository.StateRepository;
import io.samancore.utils.page.PageRequest;
import io.samancore.utils.page.PageUtil;
import io.samancore.model.State;
import io.samancore.model.PageData;
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
        var States = repository.getPageByLabel(label, pageRequest);
        return PageUtil.toPageModel(States, transformer::toModel);
    }
}
