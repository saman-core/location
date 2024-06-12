package io.samancore.repository;

import io.samancore.entity.StateEntity;
import io.samancore.model.PageData;
import io.samancore.utils.page.PageRequest;

public interface StateRepository {

    PageData<StateEntity> getPageByLabel(String label, PageRequest pageRequest);

    StateEntity getById(Long id);
}