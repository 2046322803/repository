package com.zuk.system.service;

import com.fasterxml.jackson.databind.util.JSONPObject;

public interface UserService {

	void init();

	JSONPObject login(JSONPObject reqMsg);

	JSONPObject logout(JSONPObject reqMsg);

}
