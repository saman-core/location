package io.samancore.repository;

import io.samancore.entity.ParishEntity;
import io.samancore.model.PageData;
import io.samancore.utils.page.PageRequest;

public interface ParishRepository {

    PageData<ParishEntity> getPageByLabelAndParentId(String label, Long parentId, PageRequest pageRequest);

    ParishEntity getById(Long id);
}