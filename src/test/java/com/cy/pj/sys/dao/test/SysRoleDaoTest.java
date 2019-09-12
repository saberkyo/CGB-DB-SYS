package com.cy.pj.sys.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleDaoTest {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Test
	public void testFindPageObjects() {
		String name = "运维经理";
		Integer startIndex = 0;
		Integer pageSize = 3;
		List<SysRole> list = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		for (SysRole sysRole : list) {
			System.out.println(sysRole);
		}
	}

	@Test
	public void testGetRowCount() {
		int rows = sysRoleDao.getRowCount(null);
		System.out.println(rows);
	}

	@Test
	public void testDeleteObject() {
		Integer id = 47;
		int ur = sysUserRoleDao.deleteObjectsByRoleId(id);
		int rm = sysRoleMenuDao.deleteObjectsByRoleId(id);
		int rows = sysRoleDao.deleteObject(id);
		System.out.println(ur);
		System.out.println(rm);
		System.out.println(rows);
	}

	@Test
	public void testInsertObject() {
		SysRole r = new SysRole();
		r.setName("test");
		r.setNote("testing");
		int rows = sysRoleDao.insertObject(r);
		Integer[] menuId = { 8, 24, 25 };
		sysRoleMenuDao.insertObjects(50, menuId);
		System.out.println(rows);
	}

	@Test
	public void testFindObjectById() {
		SysRoleMenuVo vo = sysRoleDao.findObjectById(50);
		System.out.println(vo);
	}

	@Test
	public void testUpdateObject() {
		SysRole r = new SysRole();
		r.setId(49);
		r.setName("test改1");
		r.setNote("testing...改1");
		r.setModifiedUser("esaka");
		int rows = sysRoleDao.updateObject(r);
		System.out.println(rows);

	}

	@Test
	public void testFindObjects() {
		List<CheckBox> list = sysRoleDao.findObjects();
		for (CheckBox checkBox : list) {
			System.out.println(checkBox);
		}
	}

}
