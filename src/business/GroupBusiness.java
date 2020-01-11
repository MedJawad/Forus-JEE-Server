package business;

import java.util.List;

import beans.Group;
import beans.User;

public interface GroupBusiness {
	public Group create(String name, String description,User admin);
	public Group findById();
	public List<Group> findAll();
	public void update();
	public void delete();
}
