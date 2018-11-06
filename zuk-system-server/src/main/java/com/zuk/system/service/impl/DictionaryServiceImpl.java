package com.zuk.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.system.dao.DictionaryDao;
import com.zuk.system.entity.Dictionary;
import com.zuk.system.service.DictionaryService;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryDao dictionaryDao;

	@Override
	public void init() {
		long count = dictionaryDao.count();
		if (0L == count) {
			List<Dictionary> dictionaryList = new ArrayList<Dictionary>();

			Dictionary dictionaryA01 = new Dictionary("A01", null, "A01", "学历", "", 0, "学历");
			Dictionary dictionaryA02 = new Dictionary("A01A01", "A01", "A01A01", "本科", "", 0, "本科");
			Dictionary dictionaryA03 = new Dictionary("A01A02", "A01", "A01A02", "大专", "", 0, "大专");
			dictionaryList.add(dictionaryA01);
			dictionaryList.add(dictionaryA02);
			dictionaryList.add(dictionaryA03);

			//dictionaryDao.saveAll(dictionaryList);
		}

	}

}
