package com.thorstiland.spielplan.service;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.User;

@Named
@Stateless
public class UserService extends CrudService<User> {
	@Inject
	CommunityService communityService;

	public UserService() {
		super(User.class);
	}

	public User load(String id) {

		User u = this.find(id);
		if (u != null) {
			return u;
		} else {
			String userId = (String) id;
			u = new User();
			u.setId(userId);
			return this.save(u);
		}
	}

	public Collection<Season> findCurrentSeasons(String userId) {
		Collection<Community> userCommunities = communityService.getByUser(this.load(userId));
		Collection<Season> seasons = new LinkedList<>();
		for (Community community : userCommunities) {
			seasons.add(communityService.findCurrentSeason(community.getId()));
		}
		return seasons;
	}

}
