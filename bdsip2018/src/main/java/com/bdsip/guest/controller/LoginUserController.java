package com.bdsip.guest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bdsip.guest.model.User;
import com.bdsip.guest.service.ifc.IGuestService;

@Controller
public class LoginUserController {

	@Resource
	IGuestService userService;
	
	@RequestMapping(value = "/login.do", produces = "application/json;charset=UTF-8")
	public ModelAndView login(User user,  HttpServletRequest request,HttpServletResponse response) {
		//
		User re = userService.getUser(user);
		if(re != null) {
			request.getSession().setAttribute("user", re);
			return new ModelAndView("manageGuest");
		}else {
			return new ModelAndView("error");
		}
	}
}
