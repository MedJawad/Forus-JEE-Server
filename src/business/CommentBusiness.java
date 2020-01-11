package business;

import java.util.List;

import beans.Comment;
import beans.Post;
import beans.User;

public interface CommentBusiness {
	public Comment create(String text, Post post,User user);
	public Comment findById(int id);
	public List<Comment> findAll();
	public void update();
	public void delete();
}
