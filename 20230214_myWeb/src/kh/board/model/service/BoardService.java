package kh.board.model.service;

import static kh.common.jdbc.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.board.model.vo.BoardVo;
import kh.common.jdbc.JdbcTemplate;
import kh.member.model.dao.BoardDao;

public class BoardService {
	
	public int getCountBoard() {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = new BoardDao().getCountBoard(conn);
		System.out.println("srv:"+ result);
		JdbcTemplate.close(conn);
		return result;
		
	}
	// overloading
	public List<BoardVo> getBoardList(int srnum, int ernum){
		List<BoardVo> result = null;
		Connection conn = JdbcTemplate.getConnection();
		result = new BoardDao().getBoardList(conn, srnum, ernum);
		System.out.println("srv:"+ result);
		JdbcTemplate.close(conn);
		return result;
	}
	
	public List<BoardVo> getBoardList(){
		List<BoardVo> result = null;
		Connection conn = JdbcTemplate.getConnection();
		result = new BoardDao().getBoardList(conn);
		System.out.println("srv:"+ result);
		JdbcTemplate.close(conn);
		return result;
	}

 
}
