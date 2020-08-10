package com.bdsip.guest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bdsip.common.dto.web.SingleResult;
import com.bdsip.guest.model.Guest;
import com.bdsip.guest.service.ifc.IGuestService;

@Controller
@RequestMapping("/guest")
public class GuestController {

	@Resource
	private IGuestService guestService;

	@RequestMapping(value = "/addGuest.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String addGuest(HttpServletRequest request, HttpServletResponse response) {
		String reqJson = request.getParameter("reqJson");
		SingleResult<Object> result = new SingleResult<>();
		Guest guest = new Guest();
		try {
			guest = JSON.parseObject(reqJson, Guest.class);
			guestService.addGuest(guest);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result.toString();
	}

	@RequestMapping(value = "/getGuestList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String getGuestList(HttpServletRequest request, HttpServletResponse response) {
		SingleResult<Object> result = new SingleResult<>();
		List<Guest> guestList = new ArrayList<>();
		// String resultMsg = "";
		try {
			guestList = guestService.getGuestList();
			result.setData(guestList);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result.toString();
	}

}
