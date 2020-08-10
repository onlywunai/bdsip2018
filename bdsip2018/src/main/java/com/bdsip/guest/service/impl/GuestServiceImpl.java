package com.bdsip.guest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdsip.guest.dao.ifc.IGuestDao;
import com.bdsip.guest.model.Guest;
import com.bdsip.guest.model.User;
import com.bdsip.guest.service.ifc.IGuestService;

@Service("guestService")
public class GuestServiceImpl implements IGuestService {

	@Resource
	private IGuestDao guestDao;

	@Override
	public void addGuest(Guest guest) {
		guestDao.addGuest(guest);
	}

	@Override
	public List<Guest> getGuestList() {
		return guestDao.getGuestList();
	}
	
	@Override
	public User getUser(User user) {
		return guestDao.getUser(user);
	}

}
