package business;

import java.util.List;

import beans.User;

public interface UserBusiness {
	public User create(String username, String email, String password);
	public User findById(int id);
	public List<User> findAll();
	public void update();
	public void delete();
	public User login(String email, String password);
}
