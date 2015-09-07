package admin.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

//@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.getWriter().append("Don't try attack this server !!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rm = request.getParameter("rememberMe");
		String requestURI = (String) session.getAttribute("requestURI");

		if (username == null || username.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號為必填欄位");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼為必填欄位");
		}

		Cookie cookieAdminUser = null;
		Cookie cookieAdminPassword = null;
		Cookie cookieAdminRememberMe = null;

		if (rm != null) {
			cookieAdminUser = new Cookie("username", username);
			cookieAdminUser.setMaxAge(60 * 60 * 24);
			cookieAdminUser.setPath(request.getContextPath());

			String encodePassword = DatatypeConverter.printBase64Binary(password.getBytes());
			cookieAdminPassword = new Cookie("password", encodePassword);
			cookieAdminPassword.setMaxAge(60 * 60 * 24);
			cookieAdminPassword.setPath(request.getContextPath());

			cookieAdminRememberMe = new Cookie("rm", "true");
			cookieAdminRememberMe.setMaxAge(60 * 60 * 24);
			cookieAdminRememberMe.setPath(request.getContextPath());
		} else {
			cookieAdminUser = new Cookie("username", username);
			cookieAdminUser.setMaxAge(60 * 60 * 24);
			cookieAdminUser.setPath(request.getContextPath());

			String encodePassword = DatatypeConverter.printBase64Binary(password.getBytes());
			cookieAdminPassword = new Cookie("password", encodePassword);
			cookieAdminPassword.setMaxAge(60 * 60 * 24);
			cookieAdminPassword.setPath(request.getContextPath());

			cookieAdminRememberMe = new Cookie("rm", "false");
			cookieAdminRememberMe.setMaxAge(60 * 60 * 24);
			cookieAdminRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieAdminUser);
		response.addCookie(cookieAdminPassword);
		response.addCookie(cookieAdminRememberMe);
		
		if(!errorMsgMap.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		// add filter here
	}
}