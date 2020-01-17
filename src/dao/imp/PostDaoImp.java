package dao.imp;

import java.util.List;

import org.hibernate.Session;

import beans.Post;
import dao.PostDao;

public class PostDaoImp implements PostDao{

	@Override
	public void create(Post pst) {

		Session sess = HibernateUtil.getInstance().openSession();
		sess.save(pst);
		sess.close();		
	}

	@Override
	public void findById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> findAll() {
		Session sess = HibernateUtil.getInstance().openSession();
		List<Post> posts = sess.createQuery("from Post").list();
		sess.close();
		return posts;
	}

	@Override
	public List<Post> findByUser(int userId) {
		Session sess = HibernateUtil.getInstance().openSession();
		List<Post> posts = sess.createQuery("from Post WHERE user= "+userId).list();
		sess.close();
		return posts;
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
