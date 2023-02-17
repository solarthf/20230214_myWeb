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
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login Post");
	// 1.
		MemberVo vo = new MemberVo();
		vo.setId(request.getParameter("id"));
		vo.setPasswd(request.getParameter("passwd"));
				
//		String id = request.getParameter("id");
//		String passwd = request.getParameter("passwd");
		
	// 2.
		MemberVo result = new MemberService().login(vo);
		if(result != null) {
			System.out.println("로그인 성공");
			
			// 세션에 아이디랑 패스워드 결과값에 저장
			request.getSession().setAttribute("lgnss", result);
			request.setAttribute("msg", vo.getId() + "님 로그인 되었습니다.");
		} else {
			System.out.println("로그인 실패");
			request.setAttribute("msg", "일치하는 아이디와 패스워드가 없습니다. 다시 확인하시고 로그인해주세요.");
		}
		
		
		// 3. 로그인 성공하면 메인화면으로 이동 및 데이터 전달 (3개 중 하나는 필수)
		// 3-1 response.sendRedirect(request.getContextPath()+"url");
		// 3-2 request.setAttribute("name", "값");
		// 3-2 request.getRequestDispatcher("xxx.jsp").forward(request, response);
		// 3-3 out.println(값); out.flush(); out.close();
		request.getRequestDispatcher("/WEB-INF/msgAlert.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/");
		
	}

	

}
