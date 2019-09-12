package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredTime;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	/**
	 * 分页查询
	 */
	@RequiredTime
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		// 验证数据的合法性
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		// 获取总记录数
		int rowCount = sysRoleDao.getRowCount(name);
		// 验证查询结果
		if (rowCount <= 0) {
			throw new ServiceException("记录不存在");
		}
		// 定义分页大小
		Integer pageSize = 3;
		// 计算开始的记录数据
		Integer startIndex = (pageCurrent - 1) * pageSize;
		// 进行分页查询
		List<SysRole> list = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		return new PageObject<SysRole>(pageCurrent, pageSize, rowCount, list);

	}

	@Override
	public int deleteObject(Integer id) {
		// 参数校验
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("id值不正确");
		}
		sysUserRoleDao.deleteObjectsByRoleId(id);
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		int rows = sysRoleDao.deleteObject(id);

		if (rows == 0) {
			throw new ServiceException("用户信息不存在");
		}

		return rows;
	}

	@Override
	public int insertObject(SysRole entity, Integer[] menuIds) {
		// 1.合法性验证
		if (entity == null)
			throw new ServiceException("保存数据不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色赋予权限");
		// 2.保存数据
		int rows = sysRoleDao.insertObject(entity);
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		// 3.返回结果
		return rows;
	}

	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		// 验证信息正确性
		if (id == null || id <= 0) {
			throw new ServiceException("id值不合法");
		}
		SysRoleMenuVo vo = sysRoleDao.findObjectById(id);
		if (vo == null) {
			throw new ServiceException("记录可能已经不存在了");
		}
		return vo;
	}

	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		if (entity == null) {
			throw new ServiceException("更新对象不能为空");
		}
		if (entity.getId() == null) {
			throw new ServiceException("更新对象id不能为空");
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("角色名不能为空");
		}
		if (menuIds == null || menuIds.length == 0) {
			throw new ServiceException("必须为角色赋予至少一个权限");
		}
		int rows = sysRoleDao.updateObject(entity);
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		return rows;
	}

	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}

}
