package dao;

import java.util.List;

import beans.Comment;

public interface CommentDao {
	public void create(Comment cmt);
	public void findById();
	public List<Comment> findAll();
	public void update();
	public void delete();
}
