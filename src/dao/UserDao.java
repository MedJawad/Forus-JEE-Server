package dao;

import java.util.List;

import beans.User;

public interface UserDao {
	public void create(User cmt);
	public void findById();
	public List<User> findAll();
	public void update();
	public void delete();
}
