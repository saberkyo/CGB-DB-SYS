package com.cy.pj.sys.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogDaoTest {

	@Autowired
	private SysLogDao sysLogDao;

	@Test
	public void testFingPageObjects() {
		List<SysLog> list = sysLogDao.fingPageObjects("admin", 0, 3);
		for (SysLog sysLog : list) {
			System.out.println(sysLog);
		}
	}

	@Test
	public void testGetRowCount() {
		int rows = sysLogDao.getRowCount("username");
		System.out.println("rows:" + rows);
	}

	@Test
	public void testDeleteObject() {
		int rows = sysLogDao.deleteObjects(24, 25);
		System.out.println("rows:" + rows);
	}

}
