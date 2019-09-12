package com.cy.pj.sys.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysUserRoleDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserRoleDaoTest {

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Test
	public void testInsertObjects() {
		Integer userId = 17;
		Integer[] roleIds = { 1, 50, 54 };
		int rows = sysUserRoleDao.insertObjects(userId, roleIds);
		System.out.println(rows);
	}

	@Test
	public void testFindRoleIdsByUserId() {
		List<Integer> roldIds = sysUserRoleDao.findRoleIdsByUserId(20);
		for (Integer integer : roldIds) {
			System.out.println(integer);
		}
	}

	@Test
	public void testDeleteObjectsByUserId() {
		int rows = sysUserRoleDao.deleteObjectsByUserId(19);
		System.out.println(rows);
	}
}
