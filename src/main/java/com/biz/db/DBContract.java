package com.biz.db;

public class DBContract {

	public static class ORACLE_PRO {
		public static final String Driver = "oracle.jdbc.driver.OracleDriver";
		public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		public static final String user = "myCBT";
		public static final String password = "1234";

	}

	public static class MYSQL_PRO{
	public static final String Driverother = "org.gjt.mm.mysql.Driver";
	public static final String Driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
	public static final String user = "root";
	public static final String password = "!aa1234";
}

}
