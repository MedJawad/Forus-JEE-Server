package dao;

import java.util.List;

import beans.Post;
import beans.User;

public interface PostDao {
	public void create(Post pst);
	public void findById();
	public List<Post> findAll();
	public void update();
	public void delete();
	public List<Post> findUserVisiblePosts(User user);
}
