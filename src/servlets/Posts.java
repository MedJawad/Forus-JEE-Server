package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Post;
import beans.User;
import business.PostBusiness;
import business.UserBusiness;
import business.imp.PostBusinessImp;
import business.imp.UserBusinessImp;
import dao.PostDao;
import dao.UserDao;
import dao.imp.PostDaoImp;
import dao.imp.UserDaoImp;

/**
 * Servlet implementation class FindPosts
 */
@WebServlet("/Posts/*")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostBusiness pBus;
	UserBusiness uBus;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		PostDao pdao = new PostDaoImp();
		this.pBus = new PostBusinessImp(pdao);
    	UserDao udao = new UserDaoImp();
		this.uBus = new UserBusinessImp(udao);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Posts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=null;
		List<Post> posts;
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		String json;
		PrintWriter out = response.getWriter();
		String path = request.getRequestURI();
		switch (path.toLowerCase()) {
			case "/forus/posts/all":
				posts = pBus.findAll();
						
				json = gson.toJson(posts);
		
				response.setContentType("text/plain");
				
				out.print(json);
				out.flush();
			break;

		default:
			if(path.toLowerCase().matches("/forus/posts/user")) {
				
				String sessionId = request.getParameter("sessionId");
				
				if (sessionId != null) { // Client provided a session Id
					HashMap activeSessions = (HashMap) this.getServletContext().getAttribute("activeSessions");
					session = (HttpSession) activeSessions.get(sessionId);
				}else {
					response.setContentType("text/plain");
					
					out.print("Session NOT FOUND");
					out.flush();
					break;
				}
				
				if(session!=null) {
					
					response.setContentType("text/plain");
					User user = (User) session.getAttribute("currentUser") ;
					if(user != null) {
						
					posts = pBus.findUserVisiblePosts( user );
					
					json = gson.toJson(posts);
					
					response.setContentType("text/plain");
					
					out.print(json);
					out.flush();					
					}else {
						response.setContentType("text/plain");
						
						out.print("No user in session ! please login !");
						out.flush();
					}
				}else {
					response.setContentType("text/plain");
					
					out.print("Wrong session Id !");
					out.flush();
				}
			break;
			}
			response.setContentType("text/plain");
			
			out.print("404 RESSOURCE NOT FOUND");
			out.flush();
			break;
		
//				User user = uBus.findById(2);
//				posts = pBus.findUserVisiblePosts(user);
//				for(Post p : posts) {
//					System.out.println(p.getId());
//				}
//	}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
