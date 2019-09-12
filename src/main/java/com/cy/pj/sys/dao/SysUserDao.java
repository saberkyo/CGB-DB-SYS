package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.entity.SysUser;

@Mapper
public interface SysUserDao {

	/**
	 * 分页查询用户信息
	 * 
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptVo> findPageObjects(@Param("username") String username, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * 获取总记录数
	 * 
	 * @param username
	 * @return
	 */
	int getRowCount(String username);

	/**
	 * 启用与禁用
	 * 
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(@Param("id") Integer id, @Param("valid") Integer valid, @Param("modifiedUser") String modifiedUser);

	/**
	 * 添加角色信息
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);

	/**
	 * 根据用户名查询用户信息及对应的部门信息
	 * 
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);

	/**
	 * 修改用户数据
	 * 
	 * @param user
	 * @return
	 */
	int updateObject(SysUser user);

	int updatePassword(@Param("password") String password, @Param("salt") String salt, @Param("id") Integer id);
}
