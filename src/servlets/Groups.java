package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.GroupBusiness;
import business.imp.GroupBusinessImp;
import dao.GroupDao;
import dao.imp.GroupDaoImp;

/**
 * Servlet implementation class Groups
 */
@WebServlet("/Groups/*")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GroupBusiness gBus;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		GroupDao gdao = new GroupDaoImp();
		this.gBus = new GroupBusinessImp(gdao);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Groups() {
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
