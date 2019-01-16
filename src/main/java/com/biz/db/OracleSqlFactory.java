package com.biz.db;

import java.util.*;

import javax.sql.*;

import org.apache.ibatis.datasource.*;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.*;
import org.apache.ibatis.transaction.jdbc.*;

import com.biz.dao.*;


public class OracleSqlFactory {
	
	SqlSessionFactory sessionFactory;
	
	public SqlSessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public OracleSqlFactory() {
		Properties props = new Properties();

		props.put("DRIVER", DBContract.ORACLE_PRO.Driver);
		props.put("URL", DBContract.ORACLE_PRO.url);
		props.put("USER", DBContract.ORACLE_PRO.user);
		props.put("PASSWORD", DBContract.ORACLE_PRO.password);

		//DataSourceFactory 만드는
		DataSourceFactory dataSourceFactory = new CbtDataSourceFactory();
		dataSourceFactory.setProperties(props);
		
		// DataSourceFactory로부터 DataSource를 요청
		DataSource dataSource = dataSourceFactory.getDataSource();

		//TransactionFactory : 데이터를 대신 받아줄 중간 창고(공장)
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		//transactionFactory,dataSource를 써서 환경만듬
		Environment environment = new Environment("GradeENV", transactionFactory, dataSource);
		
		
		Configuration config = new Configuration(environment);
		/* vo, dao만들고나서 */
		config.addMapper(CbtDao.class);
		
		
		this.sessionFactory = new SqlSessionFactoryBuilder().build(config);
	}
}
