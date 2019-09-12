package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@RestController
@RequestMapping("/role/")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjets(String name, Integer pageCurrent) {
		PageObject<SysRole> po = sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(po);
	}

	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysRoleService.deleteObject(id);
		return new JsonResult("delete ok");
	}

	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole entity, Integer[] menuIds) {
		sysRoleService.insertObject(entity, menuIds);
		return new JsonResult("save ok");
	}

	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(sysRoleService.findObjectById(id));
	}

	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole entity, Integer[] menuIds) {

		sysRoleService.updateObject(entity, menuIds);
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindRoles")
	public JsonResult doFindRoles() {
		return new JsonResult(sysRoleService.findObjects());
	}
}
