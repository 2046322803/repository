package com.zuk.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.system.dao.ResourceDao;
import com.zuk.system.entity.Resource;
import com.zuk.system.service.ResourceService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public void init() {
		long count = resourceDao.count();
		if (0L == count) {
			List<Resource> resourceList = new ArrayList<Resource>();
			Resource resourceA01 = new Resource("A01", null, "0", "A01", "设备", "", "", 0, "目录");
			resourceList.add(resourceA01);

			Resource resourceB01 = new Resource("B01", null, "0", "B01", "人事", "", "", 0, "目录");
			Resource resourceB02 = new Resource("B01B01", "B01", "1", "B01B01", "部门管理", "", "", 0, "菜单");
			Resource resourceB03 = new Resource("B01B02", "B01", "1", "B01B02", "用户管理", "", "", 0, "菜单");
			resourceList.add(resourceB01);
			resourceList.add(resourceB02);
			resourceList.add(resourceB03);

			Resource resourceC01 = new Resource("C01", null, "0", "C01", "系统", "", "", 0, "目录");
			Resource resourceC02 = new Resource("C01C01", "C01", "1", "C01C01", "资源管理", "", "", 0, "目录");
			Resource resourceC03 = new Resource("C01C02", "C01", "1", "C01C02", "角色管理", "", "", 0, "目录");
			Resource resourceC04 = new Resource("C01C03", "C01", "1", "C01C03", "字典管理", "", "", 0, "目录");
			resourceList.add(resourceC01);
			resourceList.add(resourceC02);
			resourceList.add(resourceC03);
			resourceList.add(resourceC04);

			//resourceDao.saveAll(resourceList);
		}
	}
}
