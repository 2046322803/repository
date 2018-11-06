package com.zuk.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuk.system.service.RoleService;

@RestController
@RequestMapping("/w/system/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

}
