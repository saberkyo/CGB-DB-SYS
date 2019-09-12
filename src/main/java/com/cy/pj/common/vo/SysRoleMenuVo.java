package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 封装角色以及角色对应的菜单id
 * 
 * @author Administrator
 *
 */
@Data
public class SysRoleMenuVo implements Serializable {

	private static final long serialVersionUID = -6048591348050051105L;
	private Integer id;
	private String name;
	private String note;
	private List<Integer> menuIds;
}
