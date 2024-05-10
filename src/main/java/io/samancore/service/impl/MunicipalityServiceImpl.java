package io.samancore.service.impl;

import io.samancore.service.MunicipalityService;
import io.samancore.transformer.MunicipalityTransformer;
import io.samancore.repository.MunicipalityRepository;
import io.samancore.utils.page.PageRequest;
import io.samancore.utils.page.PageUtil;
import io.samancore.model.Municipality;
import io.samancore.model.PageData;
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
        var Municipalitys = repository.getPageByLabel(label, parentId, pageRequest);
        return PageUtil.toPageModel(Municipalitys, transformer::toModel);
    }
}
