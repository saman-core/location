package io.samancore.api;

import io.samancore.service.MunicipalityService;
import io.samancore.service.ParishService;
import io.samancore.service.StateService;
import io.samancore.utils.page.PageUtil;
import io.samancore.model.State;
import io.samancore.model.Municipality;
import io.samancore.model.PageData;
import io.samancore.model.Parish;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestQuery;

@Path("")
public class LocationApi {

    @Inject
    Logger log;

    @Inject
    StateService stateService;

    @Inject
    MunicipalityService municipalityService;

    @Inject
    ParishService parishService;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/state")
    @RolesAllowed({"admin"})
    public PageData<State> getAllState(@RestQuery("label__regex") String searchValue) {
        log.debugf("StateApi.getAllMunicipality %s", searchValue);
        var pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        return stateService.getPageByLabel(searchValue, pageRequest);
    }

    @GET
    @Path("/municipality")
    @RolesAllowed({"admin"})
    public PageData<Municipality> getAllMunicipality(@RestQuery("label__regex") String searchValue, @RestQuery("parent_id") Long parentId) {
        log.debugf("StateApi.getAllMunicipality %s %d", searchValue, parentId);
        var pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        return municipalityService.getPageByLabelAndParentId(searchValue, parentId, pageRequest);
    }

    @GET
    @Path("/parish")
    @RolesAllowed({"admin"})
    public PageData<Parish> getAllParish(@RestQuery("label__regex") String searchValue, @RestQuery("parent_id") Long parentId) {
        log.debugf("StateApi.getAllParish %s %d", searchValue, parentId);
        var pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        return parishService.getPageByLabelAndParentId(searchValue, parentId, pageRequest);
    }
}