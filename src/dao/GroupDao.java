package dao;

import java.util.List;

import beans.Group;

public interface GroupDao {
	public void create(Group cmt);
	public void findById();
	public List<Group> findAll();
	public void update();
	public void delete();
}
