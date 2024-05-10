package io.samancore.repository;

import io.samancore.entity.MunicipalityEntity;
import io.samancore.model.PageData;
import io.samancore.utils.page.PageRequest;

public interface MunicipalityRepository {

    PageData<MunicipalityEntity> getPageByLabel(String label, Long parentId, PageRequest pageRequest);
}