package dao.imp;

import java.util.List;

import org.hibernate.Session;

import beans.Group;
import dao.GroupDao;

public class GroupDaoImp implements GroupDao {

	@Override
	public void create(Group grp) {
		Session sess = HibernateUtil.getInstance().openSession();
		sess.save(grp);
		sess.close();				
	}

	@Override
	public void findById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Group> findAll() {
		Session sess = HibernateUtil.getInstance().openSession();
		List<Group> groups = sess.createQuery("from Group").list();
		sess.close();
		return groups;
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
