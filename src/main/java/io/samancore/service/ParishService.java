package io.samancore.service;

import io.samancore.model.PageData;
import io.samancore.model.Parish;
import io.samancore.utils.page.PageRequest;

public interface ParishService {
    PageData<Parish> getPageByLabelAndParentId(String label, Long parentId , PageRequest pageRequest);
}