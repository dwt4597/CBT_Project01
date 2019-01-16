package com.biz.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.biz.sql.*;
import com.biz.vo.*;

public interface CbtDao {

	@Select(CbtSQL.SC_ALL)
	public List<CbtVO> selectAll();

	@Select(CbtSQL.SC_FIND_ID)
	public CbtVO findById(String id);

	@Select(CbtSQL.SC_FIND_QUESION)
	public List<CbtVO> findByQuesion(String cb_ques);

	@Insert(CbtSQL.SC_INSERT)
	public int insert(CbtVO vo);

	@Update(CbtSQL.SC_UPDATE)
	public int update(CbtVO vo);

	@Delete(CbtSQL.SC_DELETE)
	public int delete(String id);

}
