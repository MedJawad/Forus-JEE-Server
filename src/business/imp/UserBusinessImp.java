package business.imp;

import java.util.List;

import beans.Group;
import beans.User;
import business.UserBusiness;
import dao.UserDao;

public class UserBusinessImp implements UserBusiness {

	UserDao udao;
	
	public UserBusinessImp(UserDao udao) {
		this.udao = udao;
	}
	
	@Override
	public User login(String username, String password) {
		User u = udao.find(username, password);
		return u;
	}
	
	@Override
	public User create(String username, String email, String password) {
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		udao.create(u);
		return u;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return udao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return udao.findAll();
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
