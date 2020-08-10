package com.bdsip.guest.service.ifc;

import java.util.List;

import com.bdsip.guest.model.Guest;

public interface IGuestService {

	/**
	 * 新增
	 * 
	 * @param guest
	 */
	public void addGuest(Guest guest);

	/**
	 * 获取
	 * 
	 * @return
	 */
	public List<Guest> getGuestList();

}
