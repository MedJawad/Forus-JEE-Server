package business.imp;

import java.util.Date;
import java.util.List;

import beans.Group;
import beans.User;
import business.GroupBusiness;
import dao.GroupDao;

public class GroupBusinessImp implements GroupBusiness {

	GroupDao gdao;
	
	public GroupBusinessImp(GroupDao gdao) {
		this.gdao = gdao;
	}
	
	@Override
	public Group create(String name, String description, User admin) {
		Group g = new Group();
		g.setAdmin(admin);
		g.addMember(admin);
		g.setName(name);
		g.setDescription(description);
		g.setDateOfCreation( new Date() );
		gdao.create(g);
		return g;
	}

	@Override
	public Group findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> findAll() {
		return gdao.findAll();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
