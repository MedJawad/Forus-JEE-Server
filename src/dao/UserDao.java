package dao;

import java.util.List;

import beans.User;

public interface UserDao {
	public void create(User cmt);
	public User findById(int id);
	public List<User> findAll();
	public void update();
	public void delete();
	public User find(String email, String password);
}
