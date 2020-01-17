package dao;

import java.util.List;

import beans.Post;

public interface PostDao {
	public void create(Post pst);
	public void findById();
	public List<Post> findAll();
	public void update();
	public void delete();
	public List<Post> findByUser(int userId);
}
