package com.zuk.system.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zuk.system.service.ResourceService;

@Order(3)
@Component
public class ResourceInitializer implements CommandLineRunner {

	@Autowired
	private ResourceService resourceService;

	@Override
	public void run(String... args) throws Exception {
		resourceService.init();
	}

}
