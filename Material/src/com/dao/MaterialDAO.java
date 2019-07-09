package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MaterialDAO extends CommonDAO {

	public ArrayList<MaterialEntity> selectAll(String column, String keyword, int startNum, int pageCount) {
		ArrayList<MaterialEntity> list = new ArrayList<MaterialEntity>();

		String sql = " select 	b.* " + 
					" from 	(	se"
					+ "lect a.*, rownum as rnum "
				+ " 			from	(	select 		* " 
				+ " 						from 		tb_material "
				+ "							where 		" + column + " like ? "
				+ "							order by 	ref desc, "
				+ "										position asc " + "						)a" + " 		)b "
				+ " where rnum between ? and ? ";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setInt(2, startNum);
			stmt.setInt(3, startNum + pageCount - 1);

			rs = stmt.executeQuery();

			while (rs.next()) {
				MaterialEntity material = new MaterialEntity();
				material.setMno(rs.getInt("m_no"));
				material.setTitle(rs.getString("title"));
				material.setContent(rs.getString("content"));
				material.setId(rs.getString("id"));
				material.setWriteDate(rs.getDate("write_date"));
				material.setHit(rs.getInt("hit"));
				material.setRef(rs.getInt("ref"));
				material.setStep(rs.getInt("step"));
				material.setPosition(rs.getInt("position"));
				material.setMaskName(rs.getString("mask_name"));
				material.setRealName(rs.getString("real_name"));

				list.add(material);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}

	public boolean insert(String title, String content, String id, String maskName, String realName) {
		boolean result = false;

		String sql = " insert into tb_material "
				+ " values(seq_material.nextval, ?, ?, ?, sysdate, 0, seq_material.currval, 0, 0, ?, ?)";
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, id);
			stmt.setString(4, maskName);
			stmt.setString(5, realName);

			int count = stmt.executeUpdate();

			if (count == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}

		return result;
	}

	public MaterialEntity select(String mno) {
		MaterialEntity material = null;

		String sql = " select * " + " from tb_material " + " where m_no = ? ";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(mno));

			rs = stmt.executeQuery();

			if (rs.next()) {
				material = new MaterialEntity();
				material.setMno(rs.getInt("m_no"));
				material.setTitle(rs.getString("title"));
				material.setContent(rs.getString("content"));
				material.setId(rs.getString("id"));
				material.setWriteDate(rs.getDate("write_date"));
				material.setHit(rs.getInt("hit"));
				material.setRef(rs.getInt("ref"));
				material.setStep(rs.getInt("step"));
				material.setPosition(rs.getInt("position"));
				material.setMaskName(rs.getString("mask_name"));
				material.setRealName(rs.getString("real_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return material;
	}

	public boolean delete(String mno) {
		boolean result = false;

		String sql = " delete from tb_material " + " where m_no = ? ";

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(mno));

			int count = stmt.executeUpdate();

			if (count == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}

		return result;
	}

	public boolean update(String mno, String content, String title, String realName, String maskName) {
		boolean result = false;

		String sql = " update tb_material " + " set title = ?, " + " content = ? ";

		if (maskName != null) {
			sql += " ,real_name = ? " + " ,mask_name = ?";
		}

		sql += " where m_no = ? ";

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();

			int ap = 1;
			stmt = con.prepareStatement(sql);
			stmt.setString(ap++, title);
			stmt.setString(ap++, content);
			if (maskName != null) {
				stmt.setString(ap++, realName);
				stmt.setString(ap++, maskName);
			}
			stmt.setInt(ap++, Integer.parseInt(mno));

			int count = stmt.executeUpdate();

			if (count == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}

		return result;
	}

	public void positionUpdate(int ref, int position) {
		String sql = " update tb_material " + " set position = position + 1 " + " where ref = ? "
				+ " and position > ? ";

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ref);
			stmt.setInt(2, position);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}

	}

	public boolean reply(String mno, String title, String content, String id, int ref, int step, int position,
			String maskName, String realName) {

		boolean result = false;

		String sql = " insert into tb_material "
				+ " values (seq_material.nextval, ?, ?, ?, sysdate, 0, ?, ?, ?, ?, ?) ";

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, id);
			stmt.setInt(4, ref);
			stmt.setInt(5, step);
			stmt.setInt(6, position);
			stmt.setString(7, maskName);
			stmt.setString(8, realName);

			int count = stmt.executeUpdate();

			if (count == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}
		return result;
	}

	public int getTotalRow(String column, String keyword) {
		int totalRow = 0;

		String sql = " select count(*) " + " from tb_material " + " where " + column + " like ? ";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");

			rs = stmt.executeQuery();

			if (rs.next()) {

				totalRow = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(con);
			close(stmt);
		}
		return totalRow;
	}
}
