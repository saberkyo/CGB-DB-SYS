package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.entity.SysRole;

public interface SysRoleService {

	/**
	 * 分页查询
	 * 
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysRole> findPageObjects(String name, Integer pageCurrent);

	/**
	 * 基于用户id删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);

	/**
	 * 
	 * 添加
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity, Integer[] menuIds);

	/**
	 * 根据id查询角色信息以及对应的菜单id
	 * 
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);

	/**
	 * 修改角色信息
	 * 
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity, Integer[] menuIds);

	/**
	 * 查找角色id与名称
	 * 
	 * @return
	 */
	List<CheckBox> findObjects();
}
