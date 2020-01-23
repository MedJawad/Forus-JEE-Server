package business.imp;

import java.util.Date;
import java.util.List;

import beans.Group;
import beans.Post;
import beans.User;
import business.PostBusiness;
import dao.PostDao;

public class PostBusinessImp implements PostBusiness {

	PostDao pdao;
	
	public PostBusinessImp(PostDao pdao) {
		this.pdao = pdao;
	}
	
	@Override
	public Post create(String title, String text,User user) {
		Post pst = new Post();
		pst.setTitle(title);
		pst.setText(text);		
		pst.setDate(new Date());
		pst.setUser(user);		
		pdao.create(pst);
		return pst;	
	}

	@Override
	public Post create(String title, String text,User user,Group group) {
		Post pst = new Post();
		pst.setTitle(title);
		pst.setText(text);
		pst.setUser(user);		
		pst.setGroup(group);	
		pst.setDate(new Date());
		pdao.create(pst);
		return pst;	
	}
	
	@Override
	public Post findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> findAll() {
		return pdao.findAll();
	}

	@Override
	public List<Post> findUserVisiblePosts(User user) {
		return pdao.findUserVisiblePosts(user);
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
