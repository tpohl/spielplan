package com.thorstiland.spielplan.service;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Game;

@Named
public class GameService extends CrudService<Game>{

	public GameService() {
		super(Game.class);
	}

}
