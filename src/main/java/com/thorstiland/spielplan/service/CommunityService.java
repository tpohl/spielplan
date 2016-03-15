package com.thorstiland.spielplan.service;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Community;

@Named
public class CommunityService extends CrudService<Community>{

	public CommunityService() {
		super(Community.class);
	}

}
