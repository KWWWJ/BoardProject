package com.java4.boardproject.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java4.boardproject.board.domain.Board;


@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Board> mapper = new RowMapper<Board>() {

		@Override
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Board(
					rs.getInt("id"),
					rs.getInt("user_id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getInt("views"),
					rs.getInt("is_withdrew") == 1,
					rs.getTimestamp("created_at")
					);
		}
	};
	
	public void add(Board board) {
		jdbcTemplate.update(
				"insert into boards ( \"title\", \"content\", \"is_withdrew\", \"user_id\") values ( ?, ?, ?, ? )",
				board.getTitle(),
				board.getContent(),
				0,
				board.getUserId());
	}
	
	public Board get(int id) {
		return jdbcTemplate.queryForObject(
				"select * from boards where \"id\"=?", 
				mapper, id
			);
	}
	
	public List<Board> getAll(){
		return jdbcTemplate.query("select * from boards order by \"id\"", mapper);
		
	}
	
	public void update(Board board) {
		jdbcTemplate.update("update board set \"title\","
				+ "	\"content\","
				+ "	\"created_at\" where id=?",
				board.getTitle(),
				board.getContent(),
				board.getCreatedAt(),
				board.getId()
				);
	}
}
