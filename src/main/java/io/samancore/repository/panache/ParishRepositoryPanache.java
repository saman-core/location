package io.samancore.repository.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.samancore.entity.ParishEntity;
import io.samancore.repository.ParishRepository;
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
public class ParishRepositoryPanache implements PanacheRepository<ParishEntity>, ParishRepository {

    @Inject
    Logger log;

    @Override
    public PageData<ParishEntity> getPageByLabelAndParentId(String label, Long parentId, PageRequest pageRequest) {
        log.debugf("ParishRepositoryPanache.getPageByLabel %s", label);

        String search = "%" + label + "%";
        var params = new HashMap<String, Object>();
        params.put("status", GeneralStatus.ACTIVE);

        PanacheQuery<ParishEntity> query;

        if(label != null && parentId != null) {
            params.put("name", search);
            params.put("municipalityId", parentId);
            var result = LIKE_LABEL + AND + FILTER_GENERAL_STATUS + AND + FILTER_MUNICIPALITY_ID;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        } else if (parentId == null && label != null ) {
            params.put("name", search);
            var result = LIKE_LABEL + AND + FILTER_GENERAL_STATUS;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        } else if ( label == null && parentId != null) {
            params.put("municipalityId", parentId);
            var result = FILTER_MUNICIPALITY_ID + AND + FILTER_GENERAL_STATUS;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        }else {
            query = this.find(RepositoryConstants.FILTER_GENERAL_STATUS, PagePanacheUtil.generateSort(pageRequest), Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
        }
        var list = query.page(PagePanacheUtil.generatePage(pageRequest)).list();
        var total = query.count();
        return PageData.<ParishEntity>newBuilder().setData(list).setCount(total).build();
    }
}