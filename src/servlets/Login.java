package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.User;
import business.UserBusiness;
import business.imp.UserBusinessImp;
import dao.UserDao;
import dao.imp.UserDaoImp;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserBusiness ub;

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
	public Login() {
		super();

	}

	public String[] getInfosFromCookie(HttpServletRequest request) {
		Cookie cks[] = request.getCookies();
		if (cks != null) {

			int indiceusername = -1;
			int indicePassword = -1;
			for (int i = 0; i < cks.length; i++) {
				if (cks[i].getName().equals("username")) {
					indiceusername = i;
				}
				if (cks[i].getName().equals("password")) {
					indicePassword = i;
				}
			}
			if (indiceusername != -1 && indicePassword != -1 && cks[indiceusername].getValue() != null
					&& cks[indicePassword].getValue() != null) {
				String[] identifiants = { cks[indiceusername].getValue(), cks[indicePassword].getValue() };
				return identifiants;
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = null;

		String[] infos = getInfosFromCookie(req);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String sessionId = req.getParameter("sessionId");
		User u = null;

		if (sessionId == null) {// Session id not provided by request => Check if a sessionId cookie exist
			Cookie cks[] = req.getCookies();
			if (cks != null) {
				
				int index = -1;
				
				for (int i = 0; i < cks.length; i++) {
					if (cks[i].getName().equals("sessionId")) {
						index = i;
					}
				}
				if (index != -1 && cks[index].getValue() != null) {
					sessionId = cks[index].getValue();
				}
			}
		}

		if (sessionId != null) { // Client Or Cookie provided a session Id
			HashMap activeSessions = (HashMap) this.getServletContext().getAttribute("activeSessions");
			session = (HttpSession) activeSessions.get(sessionId);
		}
		


		if (session == null) { // The Client didn't provide a session Id or provided a wrong one , and session cookie not found
			session = req.getSession();
		}

		if (session != null) { // The Client didn't provided a correct session Id => setting sessionId cookie
			Cookie sessCookie = new Cookie("sessionId", session.getId());
			
			res.addCookie(sessCookie);
		}

		System.out.println("Id of session is :" + session.getId());

		if (u == null && session.getAttribute("currentUser") != null) { // Current user available in session
			System.out.println("getting user from session");
			u = (User) session.getAttribute("currentUser");
		}

		if (u == null && infos != null) {// NO CURRENT USER IN SESSION AND USER COOKIE IS FOUND

			u = ub.login(infos[0], infos[1]);

			if (u == null) { // WRONG TRY TO LOGIN WITH COOKIE

				// DELETING THE COOKIE
				Cookie cookie = new Cookie("username", "");
				cookie.setMaxAge(0);
				res.addCookie(cookie);
				cookie = new Cookie("password", "");
				cookie.setMaxAge(0);
				res.addCookie(cookie);

			} else {
				session.setAttribute("currentUser", u);
			}
		}

		if (u == null && username != null && password != null) { // No User in session , no cookie found
			u = ub.login(username, password);

			if (u == null) { // WRONG TRY TO LOGIN
				System.out.println("wrong try to login");
			} else {
				System.out.println("Successfully logged in");
				if (req.getParameter("remember") != null) {
					Cookie ck1 = new Cookie("username", username);

					Cookie ck2 = new Cookie("password", password);
					res.addCookie(ck1);
					res.addCookie(ck2);
				}
				session.setAttribute("currentUser", u);
			}
		}

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
				.create();
		Auth auth = new Auth(u, session.getId());
		String json;
		PrintWriter out = res.getWriter();

		json = gson.toJson(auth);

		res.setContentType("text/plain");

		out.print(json);
		out.flush();
//		if (u != null) {
//
//		} else {
//			res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested user not found.");
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
