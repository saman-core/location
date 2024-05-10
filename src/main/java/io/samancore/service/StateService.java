package io.samancore.service;

import io.samancore.model.PageData;
import io.samancore.model.State;
import io.samancore.utils.page.PageRequest;

public interface StateService {

    PageData<State> getPageByLabel(String label, PageRequest pageRequest);
}