package com.bdsip.guest.dao.ifc;

import java.util.List;

import com.bdsip.guest.model.Guest;

public interface IGuestDao {

	/**
	 * 新增
	 * 
	 * @param guest
	 */
	public void addGuest(Guest guest);

	/**
	 * 获取信息
	 * 
	 * @return
	 */
	public List<Guest> getGuestList();
}
