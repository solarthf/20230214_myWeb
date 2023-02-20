package kh.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.board.model.vo.BoardVo;
import static kh.common.jdbc.JdbcTemplate.*;

public class BoardDao {
	
	public int getCountBoard(Connection conn) {
		int result = 0;
		
		String sql = "SELECT COUNT(*) cnt FROM BOARD";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
//				result = rs.getInt(1);
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
		
	}
	public List<BoardVo> getBoardList(Connection conn, int srnum, int ernum){
		List<BoardVo> result = null;
		String sql = "SELECT * "
				+ "  FROM (SELECT rownum rn, tbl_1.* "
				+ "           FROM (SELECT rownum xn, BOARD_NUM, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT "
				+ "                       , BOARD_ORIGINAL_FILENAME, BOARD_RENAME_FILENAME, BOARD_DATE "
				+ "                       , BOARD_LEVEL, BOARD_REF, BOARD_REPLY_SEQ, BOARD_READCOUNT "
				+ "                   FROM BOARD "
				+ "                  ORDER BY BOARD_REF DESC, BOARD_REPLY_SEQ ASC) tbl_1) tbl_2 "
				+ " WHERE rn BETWEEN ? and ?";
		// ""안에는 ; 없어야 함. \r, \n도 없어야 함
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, srnum);
			pstmt.setInt(2, ernum);
			rs = pstmt.executeQuery();
			
			// 다중행
			result = new ArrayList<BoardVo>();
			while(rs.next()) {
				System.out.println("한행읽기");
				BoardVo vo = new BoardVo();
				vo.setBoardContent(rs.getString("board_Content"));
				vo.setBoardDate(rs.getDate("board_Date"));
				vo.setBoardLevel(rs.getInt("board_Level"));
				vo.setBoardNum(rs.getInt("board_Num"));
				vo.setBoardOriginalFilename(rs.getString("board_Original_Filename"));
				vo.setBoardReadcount(rs.getInt("board_Readcount"));
				vo.setBoardRef(rs.getInt("board_Ref"));
				vo.setBoardRenameFilename(rs.getString("board_Rename_Filename"));
				vo.setBoardReplySeq(rs.getInt("board_Reply_Seq"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				result.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("dao:"+ result);	
		return result;
	}
	public List<BoardVo> getBoardList(Connection conn){
		List<BoardVo> result = null;
		String sql = "SELECT BOARD_NUM, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT"
				+ ", BOARD_ORIGINAL_FILENAME, BOARD_RENAME_FILENAME, BOARD_DATE"
				+ ", BOARD_LEVEL, BOARD_REF, BOARD_REPLY_SEQ, BOARD_READCOUNT"
				+ " FROM BOARD"
				+ " ORDER BY BOARD_REF DESC, BOARD_REPLY_SEQ ASC";
		// ""안에는 ; 없어야 함. \r, \n도 없어야 함
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 다중행
			result = new ArrayList<BoardVo>();
			while(rs.next()) {
				System.out.println("한행읽기");
				BoardVo vo = new BoardVo();
				vo.setBoardContent(rs.getString("board_Content"));
				vo.setBoardDate(rs.getDate("board_Date"));
				vo.setBoardLevel(rs.getInt("board_Level"));
				vo.setBoardNum(rs.getInt("board_Num"));
				vo.setBoardOriginalFilename(rs.getString("board_Original_Filename"));
				vo.setBoardReadcount(rs.getInt("board_Readcount"));
				vo.setBoardRef(rs.getInt("board_Ref"));
				vo.setBoardRenameFilename(rs.getString("board_Rename_Filename"));
				vo.setBoardReplySeq(rs.getInt("board_Reply_Seq"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				result.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("dao:"+ result);	
		return result;
	}
}
