package business.imp;

import java.util.Date;
import java.util.List;

import beans.Comment;
import beans.Post;
import beans.User;
import business.CommentBusiness;
import dao.CommentDao;

public class CommentBusinessImp implements CommentBusiness {

	CommentDao cdao;
	
	public CommentBusinessImp(CommentDao cdao) {
		this.cdao = cdao;
	}
	@Override
	public Comment create(String text, Post post, User user) {
		Comment c = new Comment();
		c.setText(text);
		c.setPost(post);
		c.setUser(user);
		c.setDate(new Date());
		cdao.create(c);
		return c;
	}

	@Override
	public Comment findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findByPost(int postId) {
		return cdao.findByPost(postId);
	}

	@Override
	public List<Comment> findAll() {
		return cdao.findAll();
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
