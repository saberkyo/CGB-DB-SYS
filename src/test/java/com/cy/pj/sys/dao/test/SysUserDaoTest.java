package com.cy.pj.sys.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserDaoTest {

	@Autowired
	private SysUserDao sysUserDao;

	@Test
	public void testFindPageObjects() {
		String username = "admin";
		Integer startIndex = 0;
		Integer pageSize = 2;
		List<SysUserDeptVo> vo = sysUserDao.findPageObjects(username, startIndex, pageSize);
		for (SysUserDeptVo sysUserDeptVo : vo) {
			System.out.println(sysUserDeptVo);
		}
	}

	@Test
	public void testGetRowCount() {
		String username = "admin";
		int rows = sysUserDao.getRowCount(username);
		System.out.println(rows);
	}

	@Test
	public void testValidById() {
		Integer id = 6;
		Integer valid = 0;
		String modifiedUser = "Esaka";
		int rows = sysUserDao.validById(id, valid, modifiedUser);
		System.out.println(rows);
	}

	@Test
	public void testInsertObject() {
		SysUser su = new SysUser();
		su.setUsername("testing");
		int rows = sysUserDao.insertObject(su);
		System.out.println(rows);
	}

	@Test
	public void testFindObjectById() {
		SysUserDeptVo vo = sysUserDao.findObjectById(19);
		System.out.println(vo);
	}

	@Test
	public void testUpdateObject() {
		SysUser user = new SysUser();
		user.setId(19);
		user.setUsername("bbbb");
		user.setEmail("111@11");
		user.setMobile("123456");
		user.setModifiedUser("me");
		int rows = sysUserDao.updateObject(user);
		System.out.println(rows);
	}
}
