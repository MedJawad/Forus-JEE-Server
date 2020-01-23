package dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import beans.Group;
import beans.Post;
import dao.PostDao;
import beans.User;

public class PostDaoImp implements PostDao {

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
	public List<Post> findUserVisiblePosts(User user) {
		Session sess = HibernateUtil.getInstance().openSession();
		Query query;
		String grouplist = "";
//		 = sess.createQuery("select id from Group WHERE ");
		for (Group group : user.getGroups()) {
			if (grouplist.equals("")) {
				grouplist += group.getId();
			} else {
				grouplist += "," + group.getId();
			}
		}
		if (grouplist.equals("")) {
			query = sess.createQuery("from Post p WHERE user = :user");
			query.setParameter("user", user);
		} else {
			query = sess.createQuery("from Post p WHERE p.group IN (:groups) OR user = :user");

			query.setParameter("groups", grouplist);
			query.setParameter("user", user);
		}

		List<Post> posts = query.list();

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
