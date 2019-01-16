package com.biz.db;

import java.util.*;

import javax.sql.*;

import org.apache.ibatis.datasource.*;
import org.apache.ibatis.datasource.pooled.*;

public class CbtDataSourceFactory implements DataSourceFactory{
	Properties props;
	public void setProperties(Properties props) {
		// TODO Auto-generated method stub
		this.props=props;
	}

	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		PooledDataSource ds = new PooledDataSource();
		ds.setDriver(props.getProperty("DRIVER"));
		ds.setUrl(props.getProperty("URL"));
		ds.setUsername(props.getProperty("USER"));
		ds.setPassword(props.getProperty("PASSWORD"));
		
		return ds;
	}

}
