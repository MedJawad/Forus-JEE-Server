package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
//		HttpSession session = req.getSession();
//		User u = null;
//		if(session.getAttribute("currentUser") == null) {
//		 String[] infos = getInfosFromCookie(req);
//			if(infos == null) { // NO COOKIE
//					String username=req.getParameter("username");
//					String password=req.getParameter("password");
//		
//					if(username != null && password!=null) {
//						 u = ub.login(username, password);
//					
//							if(u == null) { // WRONG TRY TO LOGIN
//								res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested user not found.");
//								return;
//								}else {
////									if(req.getParameter("remember") != null) {
////										Cookie ck1 = new Cookie("username", username);
////										
////										Cookie ck2 = new Cookie("password", password);
////										res.addCookie(ck1);
////										res.addCookie(ck2);
////									}
//									
//									
//									session.setAttribute("currentUser", u);
//								}
//					}else { // 
//						res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested user not found.");
//						System.out.println("null username or password");
//						return;
//					}
//			
//				}else { // NO CURRENT USER IN SESSION AND COOKIE IS FOUND
////					 u = ub.login(infos[0], infos[1]);
////					if(u == null) { // WRONG TRY TO LOGIN WITH COOKIE
////						
////						
////						//DELETING THE COOKIE
////				        Cookie cookie = new Cookie("username", "");
////				        cookie.setMaxAge(0);
////				        res.addCookie(cookie);
////				        cookie = new Cookie("password", "");
////				        cookie.setMaxAge(0);
////				        res.addCookie(cookie);
////				        
////				        // FORWARDING TO LOGIN WITH ERROR MESSAGE
////						res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested user not found.");
////
////						return;
////						
////						}else {
////							session.setAttribute("currentUser", u);
////						}
//					res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested user not found.");
//
//
//				}
//	
//			}

		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User u = null;

		if (u == null && session.getAttribute("currentUser") != null) {
			System.out.println("getting user from session");
			u = (User) session.getAttribute("currentUser");
		}

		if (u == null && username != null && password != null) {
			u = ub.login(username, password);

			if (u == null) { // WRONG TRY TO LOGIN
				System.out.println("wrong try to login");
			} else {
				System.out.println("Successfully logged in");
				session.setAttribute("currentUser", u);
			}
		}

		if (u != null) {

			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
					.create();
			String json;
			PrintWriter out = res.getWriter();

			json = gson.toJson(u);

			res.setContentType("text/plain");

			out.print(json);
			out.flush();
		}else {
			res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested user not found.");
		}

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
