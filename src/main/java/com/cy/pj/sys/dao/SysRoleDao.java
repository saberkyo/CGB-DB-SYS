package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.entity.SysRole;

@Mapper
public interface SysRoleDao {

	/**
	 * 基于条件（用户名）查询当前页数据
	 * 
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysRole> findPageObjects(@Param("name") String name, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * 基于条件查询总记录数
	 * 
	 * @param name
	 * @return
	 */
	int getRowCount(@Param("name") String name);

	@Delete("delete from sys_roles where id=#{id}")
	int deleteObject(Integer id);

	/**
	 * 新增角色信息
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);

	/**
	 * 根据角色id查询，将角色信息及对应的菜单id存入vo对象
	 * 
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);

	/**
	 * 修改用户信息
	 * 
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);

	/**
	 * 查找角色id与名称
	 * 
	 * @return
	 */
	@Select("select id,name from sys_roles")
	List<CheckBox> findObjects();

}
