package dao.imp;

import java.util.List;

import org.hibernate.Session;

import beans.Comment;
import beans.Post;
import dao.CommentDao;

public class CommentDaoImp implements CommentDao {

	@Override
	public void create(Comment cmt) {
		Session sess = HibernateUtil.getInstance().openSession();
		sess.save(cmt);
		sess.close();				
	}

	@Override
	public void findById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> findByPost(int postId) {
		Session sess = HibernateUtil.getInstance().openSession();
		List<Comment> comments = sess.createQuery("from Comment WHERE post="+postId).list();
		sess.close();
		return comments;
	}

	@Override
	public List<Comment> findAll() {
		Session sess = HibernateUtil.getInstance().openSession();
		List<Comment> comments = sess.createQuery("from Comment").list();
		sess.close();
		return comments;
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
