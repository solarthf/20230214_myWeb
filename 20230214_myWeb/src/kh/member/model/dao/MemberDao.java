package kh.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kh.common.jdbc.JdbcTemplate.*;
import kh.member.model.vo.MemberVo;

public class MemberDao {

	// 내정보보기 myinfo
	public MemberVo myinfo(Connection conn, String id) {
		MemberVo result = null;
		
		String sql = "SELECT ID, NAME, EMAIL FROM TEST_MEMBER";
		sql += " WHERE ID = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
						
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new MemberVo();
				result.setEmail(rs.getString("email"));
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	// 로그인
	public MemberVo login(Connection conn, MemberVo vo) {
		MemberVo result = null;
		
		String sql = "SELECT ID, NAME, EMAIL FROM TEST_MEMBER";
		sql += " WHERE ID = ? AND PASSWD = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new MemberVo();
				result.setEmail(rs.getString("email"));
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	// 회원가입 insert문 → int로 받는다
	public int enroll(Connection conn, MemberVo vo) {
		int result = -1;
		
		// 쿼리문안에 ; 세미콜론 작성 不可
		String query = "INSERT INTO TEST_MEMBER VALUES";
		query += "(?, ?, ?, ?)";
		
		PreparedStatement pstmt= null;
		
		try {
			pstmt = conn.prepareStatement(query);
		// ? 채워주기
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
		// pstmt 실행
			
		// 결과값 result에 대입
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("DAO enroll() return: " + result);
		return result;
	}

	public int dupIdChk(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		
		String query = "SELECT COUNT(*) FROM TEST_MEMBER WHERE ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}		
		return result;
	}
}
