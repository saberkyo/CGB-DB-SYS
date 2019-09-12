package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.entity.SysUser;

public interface SysUserService {

	/**
	 * 
	 * 分页查询
	 * 
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent);

	/**
	 * 禁用启用元素
	 * 
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(Integer id, Integer valid, String modifiedUser);

	/**
	 * 新增用户信息，以及用户角色关系表
	 * 
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysUser entity, Integer[] roleIds);

	/**
	 * 根据用户id获取用户信息以及对应的角色关系
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> findObjectById(Integer userId);

	/**
	 * 更新用户信息以及对应角色关系
	 * 
	 * @param user
	 * @param roleIds
	 * @return
	 */
	int updateObject(SysUser user, Integer[] roleIds);

	/**
	 * 修改密码
	 * 
	 * @param password
	 * @param newPassword
	 * @param cfgPassword
	 * @return
	 */
	int updatePassword(String password, String newPassword, String cfgPassword);
}
