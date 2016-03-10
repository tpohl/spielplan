package com.thorstiland.spielplan.service;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Season;

@Named
public class SeasonService extends CrudService<Season>{

	public SeasonService() {
		super(Season.class);
	}

}
