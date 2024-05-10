package io.samancore.service;

import io.samancore.model.Municipality;
import io.samancore.model.PageData;
import io.samancore.utils.page.PageRequest;

public interface MunicipalityService {
    PageData<Municipality> getPageByLabelAndParentId(String label, Long parentId , PageRequest pageRequest);
}