package com.bdsip.guest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginUserController {

	@RequestMapping(value = "/login.do", produces = "application/json;charset=UTF-8")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if (name.equals("admin") && password.equals("adminbdsip")) {
			return new ModelAndView("manageGuest");
		}
		return new ModelAndView("error");
	}
}
