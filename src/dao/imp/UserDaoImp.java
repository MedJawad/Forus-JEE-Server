package dao.imp;

import java.util.List;

import org.hibernate.Session;

import beans.Post;
import beans.User;
import dao.UserDao;

public class UserDaoImp implements UserDao {

	@Override
	public void create(User user) {
		Session sess = HibernateUtil.getInstance().openSession();
		sess.save(user);
		sess.close();			
	}

	@Override
	public void findById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		Session sess = HibernateUtil.getInstance().openSession();
		List<User> users = sess.createQuery("from User").list();
		sess.close();
		return users;
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
