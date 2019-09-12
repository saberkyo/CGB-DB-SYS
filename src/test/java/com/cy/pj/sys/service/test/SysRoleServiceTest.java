package com.cy.pj.sys.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceTest {

	@Autowired
	private SysRoleService sysRoleService;

	@Test
	public void testFindPageObjects() {
		PageObject<SysRole> po = sysRoleService.findPageObjects("运维经理", 1);
		System.out.println(po);
	}

	@Test
	public void testDeleteObject() {
		Integer id = 46;
		int rows = sysRoleService.deleteObject(id);
		System.out.println(rows);
	}

	@Test
	public void testFindObjectById() {
		SysRoleMenuVo vo = sysRoleService.findObjectById(49);
		System.out.println(vo);
	}

	@Test
	public void testUpdataObject() {
		SysRole entity = new SysRole();
		entity.setId(49);
		entity.setName("test改2");
		entity.setNote("testing改2");
		entity.setModifiedUser("Esaka");
		Integer[] menuIds = { 8, 46, 47, 48 };
		int rows = sysRoleService.updateObject(entity, menuIds);
		System.out.println(rows);
	}

	@Test
	public void testFindObjects() {
		List<CheckBox> list = sysRoleService.findObjects();
		for (CheckBox checkBox : list) {
			System.out.println(checkBox);
		}
	}
}
