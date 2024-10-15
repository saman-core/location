package io.samancore.service.impl;

import io.samancore.model.PageData;
import io.samancore.model.Parish;
import io.samancore.repository.ParishRepository;
import io.samancore.service.ParishService;
import io.samancore.transformer.ParishTransformer;
import io.samancore.utils.page.PageRequest;
import io.samancore.utils.page.PageUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

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
        if (parentId == null)
            return PageData.<Parish>newBuilder().setData(List.of()).setCount(0).build();
        var parishes = repository.getPageByLabelAndParentId(label, parentId, pageRequest);
        return PageUtil.toPageModel(parishes, transformer::toModel);
    }

    @Override
    public Parish getById(Long id) {
        log.debugf("ParishServiceImpl.getById %d", id);
        var entity = repository.getById(id);
        return transformer.toModel(entity);
    }
}
