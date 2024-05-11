package io.samancore.service.impl;

import io.samancore.service.ParishService;
import io.samancore.transformer.ParishTransformer;
import io.samancore.repository.ParishRepository;
import io.samancore.utils.page.PageRequest;
import io.samancore.utils.page.PageUtil;
import io.samancore.model.Parish;
import io.samancore.model.PageData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ParishServiceImpl implements ParishService {

    @Inject
    Logger log;

    @Inject
    ParishRepository repository;

    @Inject
    ParishTransformer transformer;

    @Override
    public PageData<Parish> getPageByLabelAndParentId(String label, Long parentId, PageRequest pageRequest) {
        log.debugf("ParishServiceImpl.getPageByLabelAndParentId %s %d", label, parentId);
        var parishes = repository.getPageByLabelAndParentId(label, parentId, pageRequest);
        return PageUtil.toPageModel(parishes, transformer::toModel);
    }
}
