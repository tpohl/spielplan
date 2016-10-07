package com.thorstiland.spielplan.dto;

import java.util.Date;
import lombok.Data;


@Data
public class GameDto {

	long id;

	private Integer scoreHome;
	private Integer scoreAway;

	private Date playDate;

	private TeamDto homeTeam;

	private TeamDto awayTeam;


}
