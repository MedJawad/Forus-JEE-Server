package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Post;
import business.PostBusiness;
import business.imp.PostBusinessImp;
import dao.PostDao;
import dao.imp.PostDaoImp;

/**
 * Servlet implementation class FindPosts
 */
@WebServlet("/Posts/*")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostBusiness pBus;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		PostDao pdao = new PostDaoImp();
		this.pBus = new PostBusinessImp(pdao);
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
		List<Post> posts;
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		String json;
		PrintWriter out = response.getWriter();
		String path = request.getRequestURI();
		switch (path) {
			case "/ForUs/Posts/All":
				posts = pBus.findAll();
						
				json = gson.toJson(posts);
		
				response.setContentType("text/plain");
				
				out.print(json);
				out.flush();
			break;

		default:
			if(path.matches("/ForUs/Posts/user")) {
				response.setContentType("text/plain");
				;
				posts = pBus.findByUser( Integer.parseInt( request.getParameter("id") ) 	);
				
				json = gson.toJson(posts);
		
				response.setContentType("text/plain");
				
				out.print(json);
				out.flush();
			break;
			}
			response.setContentType("text/plain");
			
			out.print("404 RESSOURCE NOT FOUND");
			out.flush();
			break;
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
