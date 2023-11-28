package com.java4.boardproject.comment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java4.boardproject.comment.domain.Comment;


@Repository
public class CommentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Comment> mapper = new RowMapper<Comment>() {

		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Comment(
					rs.getInt("id"),
					rs.getString("content"),
					rs.getTimestamp("created_at"),
					rs.getInt("is_withdrew") == 1,
					rs.getInt("user_id"),
					rs.getInt("board_id"),
					rs.getInt("comment_id"),
					null,
					rs.getString("name")
					);
		}
	};
	
	public void add(Comment comment) {
		jdbcTemplate.update(
				"insert into comments ( \"content\", \"user_id\", \"board_id\", \"comment_id\") values ( ?, ?, ?, ? )",
				comment.getContent(),
				comment.getUserId(),
				comment.getBoardId(),
				comment.getCommentId() > 0 ? comment.getCommentId() : null
				);
	}
	
	public Comment get(int id) {
		return jdbcTemplate.queryForObject(
				"select a.*, b.\"name\" from comments a join users b on a where a.\"id\"=?", 
				mapper, id
			);
	}
	
	
	public List<Comment> getAll(){
		return jdbcTemplate.query("select * from comments order by \"id\"", mapper);
		
	}
	
	public List<Comment> getParents(int boardId){
		return jdbcTemplate.query("select a.*, b.\"name\" from comments a join users b on a.\"user_id\"=b.\"id\" where a.\"board_id\"=? and a.\"comment_id\" is null order by a.\"id\"",
//				+ " desc offset ? rows fetch first 5 rows only",
				mapper, boardId);
	}
	
	public List<Comment> getChildren(int boardId, int commentId){
		return jdbcTemplate.query("select a.*, b.\"name\" from comments a join users b on a.\"user_id\"=b.\"id\" where a.\"board_id\"=? and a.\"comment_id\"=? order by a.\"id\"",
				mapper, boardId, commentId);
	}
	
	public void update(Comment comment) {
		jdbcTemplate.update("update boards set \"content\" = ? where \"id\"=?",
				comment.getContent(),
				comment.getId()
				);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("delete from coments where \"id\"=?",
				id);
	}
}
