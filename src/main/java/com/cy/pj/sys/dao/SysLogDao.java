package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysLog;

@Mapper
public interface SysLogDao {

	List<SysLog> fingPageObjects(@Param("username") String username, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int getRowCount(@Param("username") String username);

	int deleteObjects(@Param("ids") Integer... ids);

	@Insert("insert into sys_logs values(null,#{username},#{operation},#{method},#{params},#{time},#{ip},now())")
	int saveObject(SysLog entity);
}
