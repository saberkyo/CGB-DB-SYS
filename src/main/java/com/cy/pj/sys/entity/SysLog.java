package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7431678833554653204L;
	private Integer id;
	private String username; // 用户名
	private String operation;
	private String method; // 请求方法
	private String params; // 请求参数
	private Long time; // 执行时长（ms）
	private String ip; // IP地址
	private Date createdTime; // 创建时间

}
