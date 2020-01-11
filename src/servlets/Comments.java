package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
