package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommonDAO {
	DataSource ds;
	
	public CommonDAO() {
		try {
		Context ct = new InitialContext();
		ds = (DataSource)ct.lookup("java:comp/env/oracle");
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public void close(ResultSet rs) {
		try {
			rs.close();
		}catch(Exception e) {}
	}
	
	public void close(Statement stmt) {
		try {
			stmt.close();
		}catch(Exception e) {}
	}

	public void close(Connection con) {
		try {
			con.close();
		}catch(Exception e) {}
	}
	
}
