package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO extends CommonDAO{
	
	public boolean insert(String id, String pw, String name) {
		boolean result = false;
		
		String sql = " insert into tb_member "+" values (?,?,?) ";
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
		con = ds.getConnection();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, pw);
		stmt.setString(3, name);
		
		int count = stmt.executeUpdate();
		
		if(count == 1) {
			result = true;
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(con);
		}
		return result;
	}
	
	public MemberEntity login(String id, String pw) {
		MemberEntity member = null;
		
		String sql = " select * from tb_member " + " where id = ? and pw = ? ";
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		con = ds.getConnection();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, pw);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			member = new MemberEntity();
			member.setId(rs.getString("id"));
			member.setPw(rs.getString("pw"));
			member.setName(rs.getString("name"));
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return member;
	}
		
	}

