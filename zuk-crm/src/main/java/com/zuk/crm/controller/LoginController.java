package com.zuk.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zuk.crm.bean.AclBean;
import com.zuk.crm.entity.User;
import com.zuk.crm.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(@ModelAttribute("user") User user, ModelMap model, HttpSession session) {
		AclBean aclBean = userService.validate(user);
		if (aclBean == null) {
			model.addAttribute("errorMsg", "用户名或密码不正确");
			return "login";
		} else {
			session.setAttribute("aclBean", aclBean);
			return "redirect:/home";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("aclBean");
		return "login";
	}

}
