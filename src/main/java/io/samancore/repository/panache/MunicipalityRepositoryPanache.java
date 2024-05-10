package io.samancore.repository.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.samancore.entity.MunicipalityEntity;
import io.samancore.repository.MunicipalityRepository;
import io.samancore.repository.constants.RepositoryConstants;
import io.samancore.utils.page.PagePanacheUtil;
import io.samancore.utils.page.PageRequest;
import io.samancore.model.PageData;
import io.samancore.model.type.GeneralStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import java.util.HashMap;
import java.util.Map;

import static io.samancore.repository.constants.RepositoryConstants.*;

@ApplicationScoped
public class MunicipalityRepositoryPanache implements PanacheRepository<MunicipalityEntity>, MunicipalityRepository {

    @Inject
    Logger log;

    @Override
    public PageData<MunicipalityEntity> getPageByLabel(String label, Long parentId, PageRequest pageRequest) {
        log.debugf("MunicipalityRepositoryPanache.getPageByLabel %s", label);

        String search = "%" + label + "%";
        var params = new HashMap<String, Object>();
        params.put("status", GeneralStatus.ACTIVE);

        PanacheQuery<MunicipalityEntity> query;

        if(label != null && parentId != null) {
            params.put("name", search);
            params.put("stateId", parentId);
            var result = LIKE_LABEL + AND + FILTER_GENERAL_STATUS + AND + FILTER_STATE_ID;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params); 
        } else if (label != null && parentId == null) {
            params.put("name", search);
            var result = LIKE_LABEL + AND + FILTER_GENERAL_STATUS;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        } else if (label == null && parentId != null) {
            params.put("stateId", parentId);
            var result = FILTER_STATE_ID + AND + FILTER_GENERAL_STATUS;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        }else {
            query = this.find(RepositoryConstants.FILTER_GENERAL_STATUS, PagePanacheUtil.generateSort(pageRequest), Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
        }
        var list = query.page(PagePanacheUtil.generatePage(pageRequest)).list();
        var total = query.count();
        return PageData.<MunicipalityEntity>newBuilder().setData(list).setCount(total).build();
    }
}