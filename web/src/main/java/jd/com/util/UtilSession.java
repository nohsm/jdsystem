package jd.com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * UtilSession.java
 * </pre>
 *
 * @ClassName   : UtilSession.java
 * @Description : UtilSession.java
 * @author SMJ
 * @since 2016. 1. 14.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 14.        SMJ                 CREATE
 * </pre>
 */
public class UtilSession {
	
	/**
	 * setSession
	 *
	 * @param request
	 * @param sessionName
	 * @param sessionIp
	 * @param sessionObj
	 */
	public void setSession(HttpServletRequest request, String sessionName, String sessionIp, Object sessionObj) {

		HttpSession session = request.getSession(true);
		session.setAttribute(sessionName, sessionObj);
		session.setAttribute(sessionIp, request.getRemoteAddr());
	}

	/**
	 * getSession
	 *
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public Object getSession(HttpServletRequest request, String sessionName) {

		HttpSession session = request.getSession();
		return session.getAttribute(sessionName);
	}

	/**
	 * removeSession
	 *
	 * @param request
	 * @param sessionName
	 * @param sessionIp
	 */
	public void removeSession(HttpServletRequest request, String sessionName, String sessionIp) {

		HttpSession session = request.getSession(true);
		session.removeAttribute(sessionName);
		session.removeAttribute(sessionIp);
		session.invalidate();
	}
}