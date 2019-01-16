package com.biz.sql;

public class CbtSQL {

	public static final String SC_ALL 
	= " SELECT * FROM tbl_cbt ";

	public static final String SC_FIND_ID 
	= " SELECT * FROM tbl_cbt WHERE cb_id = #{cb_id} ";

	public static final String SC_FIND_QUESION
	= " SELECT * FROM tbl_cbt WHERE cb_ques = #{cb_ques} ";

	public static final String SC_INSERT 
	= " INSERT INTO tbl_cbt(cb_id,cb_ques,cb_ex,cb_ans) "
		+ " VALUES(SEQ_ID.NEXTVAL, #{cb_ques}, #{cb_ex}, #{cb_ans}) ";
	
	public static final String SC_UPDATE 
	= " UPDATE tbl_cbt " 
	+ " SET cb_ques = #{cb_ques} "
//	+ " cb_ex = #{cb_ex}, " 
//	+ " cb_ans = #{cb_ans} " 
	+ " WHERE cb_id = #{cb_id} ";

	public static final String SC_DELETE 
	= " DELETE FROM tbl_cbt WHERE cb_id = #{cb_id} ";

}
