package kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.member.model.service.MemberService;
import kh.member.model.vo.MemberVo;

/**
 * Servlet implementation class MyinfoController
 */
@WebServlet("/myinfo")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1
		String id = null;
		if(request.getSession().getAttribute("lgnss") != null) {
			id = ((MemberVo)(request.getSession().getAttribute("lgnss"))).getId();
		}

		// 2 나의 id에 해당하는 정보를 DB에서 읽어오기
		if(id != null) {
			request.setAttribute("myinfo", new MemberService().myinfo(id));
			// 3. 로그인 성공하면 메인화면으로 이동 및 데이터 전달 (3개 중 하나는 필수)
			// 3-1 response.sendRedirect(request.getContextPath()+"url");
			// 3-2 request.setAttribute("name", "값");
			// 3-2 request.getRequestDispatcher("xxx.jsp").forward(request, response);
			// 3-3 out.println(값); out.flush(); out.close();
			
			request.getRequestDispatcher("/WEB-INF/view/member/myinfo.jsp").forward(request, response);
		} else {
			// 방법 1 : 로그인 정보가 없을 때, 많은 jsp페이지에서 같은 코드를 작성해야하는 불편함이 있음
			// 방법 2 : 로그인 정보가 없을 때. 하나의 error 페이지를 만들어줌
			request.setAttribute("errorMsg", "로그인되지 않아 정보를 확인할 수 없습니다.");
			request.getRequestDispatcher("/WEB-INF/view/error/errorLogin.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
