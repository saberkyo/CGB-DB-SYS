package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Transactional(timeout = 30, rollbackFor = Throwable.class, noRollbackFor = IllegalArgumentException.class, isolation = Isolation.READ_COMMITTED)
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Transactional(readOnly = true) // 允许并发读
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		if (list == null || list.size() == 0) {
			throw new ServiceException("没有对应菜单信息");
		}
		return list;
	}

	@Override
	public int getChildCount(Integer id) {
		int count = sysMenuDao.getChildCount(id);
		return count;
	}

	@Transactional // 标识作用
	@Override
	public int deleteObject(Integer id) {
		// 参数校验
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("请先选择");
		}
		// 基于id查询子菜单数并校验
		int count = getChildCount(id);
		if (count > 0) {
			throw new ServiceException("请先删除子菜单");
		}
		// 删除菜单
		int rows = sysMenuDao.deleteObject(id);
		if (rows == 1) {
			throw new ServiceException("此菜单可能已经不存在了");
		}
		// 删除菜单角色关系表
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		return rows;
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}

	@Override
	public int insertObject(SysMenu entity) {
		// 参数校验
		if (entity == null) {
			throw new ServiceException("保存对象不能为空");
		}
		if (entity.getName() == null || entity.getName().equals("")) {
			throw new ServiceException("菜单名不能为空");
		}
		int rows;
		try {
			rows = sysMenuDao.insertObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		return rows;
	}

	@Override
	public int updateObject(SysMenu entity) {
		if (entity == null) {
			throw new ServiceException("保存对象不能为空");
		}
		if (entity.getName() == null || entity.getName().equals("")) {
			throw new ServiceException("菜单名不能为空");
		}
		int rows = sysMenuDao.updateObject(entity);
		if (rows == 0) {
			throw new ServiceException("记录可能已经不存在了");
		}

		return rows;
	}

}
