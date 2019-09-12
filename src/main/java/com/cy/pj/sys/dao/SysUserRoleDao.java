package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

	@Delete("delete from sys_user_roles where role_id=#{roleId}")
	int deleteObjectsByRoleId(Integer roleId);

	int insertObjects(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);

	/**
	 * 根据用户id查询对应的角色ids
	 * 
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer userId);

	/**
	 * 根据用户id删除对应用户角色关系
	 * 
	 * @param userId
	 * @return
	 */
	int deleteObjectsByUserId(Integer userId);

}
