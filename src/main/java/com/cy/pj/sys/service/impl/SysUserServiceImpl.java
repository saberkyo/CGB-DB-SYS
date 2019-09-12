package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1) {
			throw new ServiceException("参数不合法");
		}

		int rowCount = sysUserDao.getRowCount(username);
		if (rowCount <= 0) {
			throw new ServiceException("记录不存在");
		}
		Integer pageSize = 2;
		Integer startIndex = (pageCurrent - 1) * pageSize;
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		return new PageObject<SysUserDeptVo>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	@RequiresPermissions("sys:user:valid")
	@RequiredLog("禁用启用")
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if (id == null || id <= 0) {
			throw new ServiceException("非法id" + id);
		}
		if (valid != 0 && valid != 1) {
			throw new ServiceException("非法valid" + valid);
		}
		if (StringUtils.isEmpty(modifiedUser)) {
			throw new ServiceException("修改用户不能为空");
		}
		int rows = 0;
		try {
			rows = sysUserDao.validById(id, valid, modifiedUser);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统正忙，请稍后重试");
		}
		return rows;
	}

	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		if (entity == null) {
			throw new ServiceException("保存对象不能为空");
		}
		if (StringUtils.isEmpty(entity.getUsername())) {
			throw new ServiceException("用户名不能为空");
		}
		if (StringUtils.isEmpty(entity.getPassword())) {
			throw new ServiceException("用户密码不能为空");
		}
		if (roleIds == null || roleIds.length == 0) {
			throw new ServiceException("请至少选择一个用户角色");
		}

		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		SimpleHash sHash = new SimpleHash("MD5", entity.getPassword(), salt, 1);
		entity.setPassword(sHash.toHex());
		int row = 0;
		try {
			row = sysUserDao.insertObject(entity);
			sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统正忙，请稍后重试");
		}

		return row;
	}

	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		if (userId == null || userId < 0) {
			throw new IllegalArgumentException("非法参数" + userId);
		}
		SysUserDeptVo vo = sysUserDao.findObjectById(userId);
		if (vo == null) {
			throw new ServiceException("此用户已经不存在");
		}
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", vo);
		map.put("roleIds", roleIds);
		return map;
	}

	@Override
	public int updateObject(SysUser user, Integer[] roleIds) {
		if (user == null) {
			throw new IllegalAccessError("保存对象不能为空");
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if (roleIds == null || roleIds.length == 0) {
			throw new IllegalArgumentException("至少为用户指定一个角色");
		}
		int rows = sysUserDao.updateObject(user);
		sysUserRoleDao.deleteObjectsByUserId(user.getId());
		sysUserRoleDao.insertObjects(user.getId(), roleIds);
		return rows;
	}

	@Override
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		if (StringUtils.isEmpty(newPassword)) {
			throw new IllegalArgumentException("新密码不能为空");
		}
		if (StringUtils.isEmpty(cfgPassword)) {
			throw new IllegalArgumentException("确认密码不能为空");
		}
		if (!newPassword.equals(cfgPassword)) {
			throw new IllegalArgumentException("新密码与确认密码不相同");
		}
		if (StringUtils.isEmpty(password)) {
			throw new IllegalArgumentException("旧密码不能为空");
		}
		// 获取登录用户
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		SimpleHash sh = new SimpleHash("MD5", password, user.getSalt(), 1);
		if (!user.getPassword().equals(sh.toHex())) {
			throw new IllegalArgumentException("密码输入错误");
		}
		String salt = UUID.randomUUID().toString();
		sh = new SimpleHash("MD5", cfgPassword, salt, 1);
		int rows = sysUserDao.updatePassword(sh.toHex(), salt, user.getId());
		return rows;
	}

}
