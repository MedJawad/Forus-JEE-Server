package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Comment;
import beans.Post;
import business.CommentBusiness;
import business.imp.CommentBusinessImp;
import dao.CommentDao;
import dao.imp.CommentDaoImp;

/**
 * Servlet implementation class Comments
 */
@WebServlet("/Comments/*")
public class Comments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentBusiness cBus;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		CommentDao cdao = new CommentDaoImp();
		this.cBus = new CommentBusinessImp(cdao);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comment> comments;
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		String json;
		PrintWriter out = response.getWriter();
		String path = request.getRequestURI();
		switch (path) {
			case "" :
			break;

		default:
			if(path.matches("/ForUs/Comments/Post")) {
				response.setContentType("text/plain");
				;
				comments = cBus.findByPost( Integer.parseInt( request.getParameter("id") ) );
				
				json = gson.toJson(comments);
		
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
