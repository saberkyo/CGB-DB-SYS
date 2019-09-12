package com.cy.pj.sys.service.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuServiceTest {

	@Autowired
	private SysMenuService sysMenuService;

	@Test
	public void testFindObjects() {
		List<Map<String, Object>> list = sysMenuService.findObjects();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testDeleteObject() {
		int count = sysMenuService.getChildCount(46);
		if (count > 0) {
			throw new ServiceException("请先删除子菜单");
		}
		int rows = sysMenuService.deleteObject(46);
		System.out.println("影响行数：" + rows);
	}

	@Test
	public void testFindZtreeMenuNodes() {
		List<Node> list = sysMenuService.findZtreeMenuNodes();
		for (Node node : list) {
			System.out.println(node);
		}
	}

	@Test
	public void testInsertObject() {
		SysMenu entity = new SysMenu();
		entity.setName("测试用2");
		entity.setUrl("127.0.0.1");
		entity.setParentId(46);
		int rows = sysMenuService.insertObject(entity);
		System.out.println("影响行数:" + rows);
	}

	@Test
	public void testUpdateObject() {
		SysMenu entity = new SysMenu();
		entity.setName("esaka");
		entity.setType(1);
		entity.setId(999);
		entity.setUrl("www.bilibili.com");
		entity.setNote("测试update");
		int rows = sysMenuService.updateObject(entity);
		System.out.println("影响行数:" + rows);
	}
}
