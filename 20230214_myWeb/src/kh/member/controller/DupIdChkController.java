package kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.member.model.service.MemberService;

/**
 * Servlet implementation class DupIdChkServlet
 */
@WebServlet("/dupid.lo")
public class DupIdChkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DupIdChkController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 1. 전달받은 값 꺼내기
		String id = request.getParameter("id");
		System.out.println("전달받은 데이터: " + id);
		
		// TODO
		// 2. DB 갔다오기
		int result = new MemberService().dupIdChk(request.getParameter("id"));
		
		// 3. 결과 값에 따라 데이터를 전달(페이지 이동 안함)
		PrintWriter out = response.getWriter();
		if(result > 0) {
			 out.append("1"); // 만약 dupIdChk()의 결과값이 0 이상이면 ‘fail’
		} else {
			 out.append("0"); // 결과값이 0 보다 크지 않으면, ‘ok’를 담아서 보낸다. 
		}
		
		out.flush();
		out.close();
		
		
		
		
		
		
	}

}
