package com.cy.pj.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1745247964423598916L;
	private Integer id; // 菜单id
	private String name; // 菜单名字
	private Integer parentId; // 上级菜单id
}
