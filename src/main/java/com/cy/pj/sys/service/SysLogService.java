package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;

public interface SysLogService {
	/**
	 * 
	 * 实现分页查询
	 * 
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);

	/**
	 * 实现数据删除操作
	 * 
	 * @param ids
	 * @return
	 */
	int deleteObjects(Integer... ids);

	int saveObject(SysLog entity);
}
