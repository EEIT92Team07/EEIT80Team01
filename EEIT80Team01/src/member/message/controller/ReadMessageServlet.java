package member.message.controller;

import global.GlobalService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import support.model.SupportBean;
import member.model.MemberBean;
import member.model.MessageBean;
import member.model.MessageService;


@WebServlet("/member/message/msg.jsp")
public class ReadMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReadMessageServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String t = request.getParameter("t");
		MessageService service = new MessageService();
		MessageBean bean = null;
		if(t!=null){
			long messageNumber= Long.parseLong(t);
			bean = service.findMessageByMessageno(messageNumber);			
		} else {
			RequestDispatcher rd =  request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;	
		}
		
		if(bean!=null){
			HttpSession session = request.getSession();
			MemberBean mb = (MemberBean) session.getAttribute(GlobalService.LOGIN_TOKEN);
			SupportBean support = (SupportBean) session.getAttribute(GlobalService.LOGIN_TOKEN_SUPPORT);
			if(support!=null || bean.getSender().equals(mb.getUserName()) && (bean.getVisibility() & 2)==0 || bean.getReceiver().equals(mb.getUserName()) && (bean.getVisibility() & 1)==0){
				request.setAttribute("Message", bean);
			} else {
				RequestDispatcher rd =  request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				return;
			}
		} else {
			RequestDispatcher rd =  request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;	
		}
		RequestDispatcher rd =  request.getRequestDispatcher("showmessage.jsp");
		rd.forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

}
