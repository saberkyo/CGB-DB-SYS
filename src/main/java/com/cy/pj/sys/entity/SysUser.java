package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1521451132974749416L;
	private Integer id;
	private String username;
	private String password;
	private String salt;// 盐值，与密码一起加密，保证密码更加安全
	private String email;
	private String mobile;
	private Integer valid = 1;
	private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
