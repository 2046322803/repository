package com.zuk.system.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zuk.system.service.DictionaryService;

@Order(4)
@Component
public class DictionaryInitializer implements CommandLineRunner {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void run(String... args) throws Exception {
		dictionaryService.init();
	}

}
