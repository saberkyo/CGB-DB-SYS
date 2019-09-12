package com.cy.pj.sys.dao.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.entity.SysMenu;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuDaoTest {

	@Autowired
	private SysMenuDao sysMenuDao;

	@Test
	public void testFindObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	@Test
	public void testGetChildCount() {
		int count = sysMenuDao.getChildCount(46);
		System.out.println("共有个" + count + "子菜单");
	}

	@Test
	public void testDeleteObject() {
		int count = sysMenuDao.getChildCount(46);
		if (count > 0) {
			throw new ServiceException("清先删除子菜单");
		}
		int rows = sysMenuDao.deleteObject(46);
		System.out.println("删除行数:" + rows);
	}

	@Test
	public void testFindZtreeMenuNodes() {

		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		for (Node node : list) {
			System.out.println(node);
		}
	}

	@Test
	public void testInsertObject() {
		SysMenu entity = new SysMenu();
		entity.setName("测试用");
		entity.setUrl("127.0.0.1");
		entity.setParentId(46);
		int row = sysMenuDao.insertObject(entity);
		System.out.println("影响行数:" + row);
	}

	@Test
	public void testUpdateObject() {
		SysMenu entity = new SysMenu();
		entity.setName("esaka");
		entity.setType(1);
		entity.setId(135);
		entity.setUrl("www.bilibili.com");
		entity.setNote("测试update");
		int rows = sysMenuDao.updateObject(entity);
		System.out.println("影响行数:" + rows);
	}
}
