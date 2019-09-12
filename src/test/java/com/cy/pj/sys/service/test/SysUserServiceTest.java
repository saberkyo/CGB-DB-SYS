package com.cy.pj.sys.service.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {

	@Autowired
	private SysUserService sysUserService;

	@Test
	public void testFindPageObjects() {
		String username = "";
		Integer pageCurrent = 3;
		PageObject<SysUserDeptVo> po = sysUserService.findPageObjects(username, pageCurrent);
		System.out.println(po);
	}

	@Test
	public void testValidById() {
		int rows = sysUserService.validById(6, 1, "admin");
		System.out.println(rows);
	}

	@Test
	public void testSaveObject() {
		SysUser entity = new SysUser();
		entity.setUsername("test2");
		entity.setPassword("这是密码");
		Integer[] roleIds = { 45, 49, 50 };
		int rows = sysUserService.saveObject(entity, roleIds);
		System.out.println(rows);
	}

	@Test
	public void testFindObjectById() {
		Integer userId = 20;
		Map<String, Object> map = sysUserService.findObjectById(userId);
		System.out.println(map);
	}

	@Test
	public void testUpdateObject() {
		SysUser user = new SysUser();
		user.setId(19);
		user.setUsername("cccc");
		user.setDeptId(4);
		Integer[] roleIds = { 45, 49, 50 };
		int rows = sysUserService.updateObject(user, roleIds);
		System.out.println(rows);
	}
}
