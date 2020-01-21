package listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.User;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public SessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
        HttpSession    session = se.getSession();
        ServletContext context = session.getServletContext();
        HashMap activeSessions =  (HashMap)context.getAttribute("activeSessions");

        activeSessions.put(session.getId(), session);
        context.setAttribute("activeSessions", activeSessions);
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession    session = se.getSession();
        ServletContext context = session.getServletContext();
        HashMap activeSessions = (HashMap)context.getAttribute("activeSessions");
        activeSessions.remove(session.getId());
	}

}
