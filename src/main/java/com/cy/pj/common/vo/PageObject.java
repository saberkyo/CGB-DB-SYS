package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7316224976001986636L;

	private Integer pageCurrent = 1;// 当前页码值
	private Integer pageSize = 3;// 页面大小
	private Integer rowCount = 0;// 总行数
	private Integer pageCount = 0;// 总页数
	private List<T> records;// 当前页记录

	public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		this.pageCount = (rowCount - 1) / pageSize + 1;
	}

}
