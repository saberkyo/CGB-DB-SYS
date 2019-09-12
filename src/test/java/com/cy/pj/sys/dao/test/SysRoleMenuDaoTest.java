package com.cy.pj.sys.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysRoleMenuDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleMenuDaoTest {

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Test
	public void testDeleteObjectsByMenuId() {
		int rows = sysRoleMenuDao.deleteObjectsByMenuId(233);
		System.out.println("影响行数：" + rows);
	}

}
