package com.thorstiland.spielplan.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.service.CommunityService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Community" })
@Named
@Stateless
@Path("community")
public class CommunityEndpoint {
	@Inject
	CommunityService communityService;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON }) 
    public List<Community> getAll() {
        return communityService.findAll();
    }
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public Community get(@PathParam("id") long id) {
        return communityService.find(id);
    }
	
	
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Community post(Community community) {
        return communityService.save(community);
    }
	
	@PUT
	@Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Community put(@PathParam("id") long id,Community community) {
		community.setId(id);
        return communityService.merge(community);
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") long id) {
         communityService.delete(id);
    }
}
