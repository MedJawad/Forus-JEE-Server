package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.UserBusiness;
import business.imp.UserBusinessImp;
import dao.UserDao;
import dao.imp.UserDaoImp;


/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserBusiness ub ;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		UserDao udao = new UserDaoImp();
		this.ub = new UserBusinessImp(udao);
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getSession(false).removeAttribute("currentUser");
		req.getSession(false).invalidate();
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        cookie = new Cookie("password", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        cookie = new Cookie("sessionId", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
		System.out.println("logout succeeded");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
