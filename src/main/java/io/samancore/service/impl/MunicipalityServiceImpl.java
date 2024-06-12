package io.samancore.service.impl;

import io.samancore.model.Municipality;
import io.samancore.model.PageData;
import io.samancore.repository.MunicipalityRepository;
import io.samancore.service.MunicipalityService;
import io.samancore.transformer.MunicipalityTransformer;
import io.samancore.utils.page.PageRequest;
import io.samancore.utils.page.PageUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MunicipalityServiceImpl implements MunicipalityService {

    @Inject
    Logger log;

    @Inject
    MunicipalityRepository repository;

    @Inject
    MunicipalityTransformer transformer;

    @Override
    public PageData<Municipality> getPageByLabelAndParentId(String label, Long parentId, PageRequest pageRequest) {
        log.debugf("MunicipalityServiceImpl.getPageByLabelAndParentId %s %s", label, parentId);
        var municipalities = repository.getPageByLabel(label, parentId, pageRequest);
        return PageUtil.toPageModel(municipalities, transformer::toModel);
    }

    @Override
    public Municipality getById(Long id) {
        log.debugf("MunicipalityServiceImpl.getById %d", id);
        var entity = repository.getById(id);
        return transformer.toModel(entity);
    }
}
