package business;

import java.util.List;

import beans.Group;
import beans.Post;
import beans.User;

public interface PostBusiness {
	public Post create(String title, String text,User user);
	public Post create(String title, String text,User user,Group group);
	public Post findById();
	public List<Post> findAll();
	public void update();
	public void delete();
	public List<Post> findUserVisiblePosts(User user);
}
