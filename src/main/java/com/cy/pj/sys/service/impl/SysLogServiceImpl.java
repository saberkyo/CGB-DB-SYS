package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		int rowCount = sysLogDao.getRowCount(username);
		if (rowCount == 0) {
			throw new ServiceException("没有对应记录");
		}

		int pageSize = 3;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<SysLog> list = sysLogDao.fingPageObjects(username, startIndex, pageSize);
		return new PageObject<SysLog>(pageCurrent, pageSize, rowCount, list);
	}

	@Override
	public int deleteObjects(Integer... ids) {
		if (ids == null || ids.length == 0) {
			throw new IllegalArgumentException("请选择一个");
		}
		int rows;
		try {
			rows = sysLogDao.deleteObjects(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("系统故障，正在恢复");
		}

		if (rows == 0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;
	}

	@Override
	public int saveObject(SysLog entity) {
		int rows = sysLogDao.saveObject(entity);
		return rows;
	}

}
