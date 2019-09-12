package com.cy.pj.sys.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogServiceTest {

	@Autowired
	private SysLogService sysLogService;

	@Test
	public void tessFindPageObjects() {
		PageObject<SysLog> po = sysLogService.findPageObjects("admin", 3);
		List<SysLog> list = po.getRecords();
		for (SysLog sysLog : list) {
			System.out.println(sysLog);
		}
	}

	@Test
	public void testDeleteObject() {
		int rows = sysLogService.deleteObjects(24, 25, 26);
		System.out.println(rows);
	}
}
