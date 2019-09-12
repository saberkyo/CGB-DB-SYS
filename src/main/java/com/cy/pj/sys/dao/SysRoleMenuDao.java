package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {

	/**
	 * 根据菜单id删除角色菜单关系数据
	 * 
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);

	/**
	 * 根据角色id删除角色菜单关系数据
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);

	/**
	 * 新增角色-菜单关系表
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	int insertObjects(@Param("roleId") Integer roleId, @Param("menuId") Integer[] menuId);

	int findMenuIdsByRoleId(Integer role_id);

}
