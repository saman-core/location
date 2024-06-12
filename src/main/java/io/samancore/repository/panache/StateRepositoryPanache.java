package io.samancore.repository.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.samancore.entity.StateEntity;
import io.samancore.model.PageData;
import io.samancore.model.type.GeneralStatus;
import io.samancore.repository.StateRepository;
import io.samancore.repository.constants.RepositoryConstants;
import io.samancore.utils.page.PagePanacheUtil;
import io.samancore.utils.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class StateRepositoryPanache implements PanacheRepository<StateEntity>, StateRepository {

    @Inject
    Logger log;

    @Override
    public PageData<StateEntity> getPageByLabel(String label, PageRequest pageRequest) {
        log.debugf("StateRepositoryPanache.getPageByLabel %s", label);

        String search = "%" + label + "%";
        var params = new HashMap<String, Object>();
        params.put("name", search);
        params.put("status", GeneralStatus.ACTIVE);

        PanacheQuery<StateEntity> query;
        if (label != null) {
            var result = RepositoryConstants.LIKE_LABEL + RepositoryConstants.AND + RepositoryConstants.FILTER_GENERAL_STATUS;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        } else {
            query = this.find(RepositoryConstants.FILTER_GENERAL_STATUS, PagePanacheUtil.generateSort(pageRequest), Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
        }

        var list = query.page(PagePanacheUtil.generatePage(pageRequest)).list();
        var total = query.count();
        return PageData.<StateEntity>newBuilder().setData(list).setCount(total).build();
    }

    @Override
    public StateEntity getById(Long id) {
        log.debugf("StateRepositoryPanache.getById %d", id);
        return this.findById(id);
    }
}