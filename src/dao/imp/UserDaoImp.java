package dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import beans.Post;
import beans.User;
import dao.UserDao;

public class UserDaoImp implements UserDao {

	@Override
	public User find(String username, String password) {
		Session sess = HibernateUtil.getInstance().openSession();
		Query q = sess.createQuery("from User WHERE username=:username AND password=:password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		User u = (User) q.uniqueResult();
		sess.close();
		return u;
	}
	
	@Override
	public void create(User user) {
		Session sess = HibernateUtil.getInstance().openSession();
		sess.save(user);
		sess.close();			
	}

	@Override
	public User findById(int id) {
		Session sess = HibernateUtil.getInstance().openSession();
		User user = (User) sess.createQuery("from User where id="+id).uniqueResult();
		sess.close();
		return user;
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
