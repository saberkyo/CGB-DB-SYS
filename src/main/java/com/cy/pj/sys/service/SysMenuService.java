package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

/**
 * 菜单服务层接口
 * 
 * @author Administrator
 *
 */
public interface SysMenuService {

	/**
	 * 查询菜单所有信息及父菜单id
	 * 
	 * @return
	 */
	List<Map<String, Object>> findObjects();

	/**
	 * 根据id查询父菜单下的子菜单数
	 * 
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);

	/**
	 * 根据id删除菜单信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);

	/**
	 * 列出所有菜单节点
	 * 
	 * @return
	 */
	List<Node> findZtreeMenuNodes();

	/**
	 * 新增菜单
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);

	/**
	 * 更新菜单信息
	 * 
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
}
