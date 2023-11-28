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
					rs.getTimestamp("created_at"),
					rs.getString("user_name"),
					rs.getString("user_nick"),
					rs.getString("user_git_address")
					);
		}
	};
	
	public void add(Board board) {
		jdbcTemplate.update(
				"insert into boards ( \"title\", \"content\", \"is_withdrew\", \"user_id\") values ( ?, ?, ?, ? )",
				board.getTitle(),
				board.getContent(),
				board.isWithdrew() ? 1:0,
				board.getUserId());
	}
	
	public Board get(int id) {
		return jdbcTemplate.queryForObject(
				"select a.*, b.\"name\" as \"user_name\", b.\"user_id\" as \"user_nick\", b.\"git_address\" as \"user_git_address\" from boards a join users b on a.\"user_id\"=b.\"id\" where a.\"id\"=?", 
				mapper, id
			);
	}
	
	
	public List<Board> getAll(){
		return jdbcTemplate.query("select a.*, b.\"name\" as \"user_name\", b.\"user_id\" as \"user_nick\", b.\"git_address\" as \"user_git_address\" from boards a join users b on a.\"user_id\"=b.\"id\" order by a.\"id\"", mapper);
		
	}
	
	public List<Board> getAll(int start){
		String qureyStart = "select a.*, b.\"name\" as \"user_name\", b.\"user_id\" as \"user_nick\", b.\"git_address\" as \"user_git_address\" from boards a join users b on a.\"user_id\"=b.\"id\" order by a.\"id\" desc offset "+String.valueOf(start)+" row fetch first 10 row only";
		return jdbcTemplate.query(qureyStart, mapper);
		
	}
	
	public void update(Board board) {
		jdbcTemplate.update("update boards set \"title\" = ?, \"content\" = ? where \"id\"=?",
				board.getTitle(),
				board.getContent(),
				board.getId()
				);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("delete from boards where \"id\"=?",
				id);
	}
	
	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from boards", Integer.class);
	}
	
}
