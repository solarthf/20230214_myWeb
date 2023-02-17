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
 * Servlet implementation class EnrollController
 */
@WebServlet("/enroll")
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/enroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 받아오는 값 한글 인코딩 (filter 파일 적용하니까 지운다)
		//request.setCharacterEncoding("UTF-8");
		
	// 1. JSP에서 전달받은 데이터(jsp에서 name값) 읽어 변수에 담기
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// 전달받은 데이터들 Vo에 담기
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPasswd(passwd);
		vo.setName(name);
		vo.setEmail(email);
	
		System.out.println("Ctrl param: "+ vo);
		
	// 2. DB 갔다오기
		int result = new MemberService().enroll(vo);
		
		if(result < 1) { // 1 = insert 성공
			System.out.println("!!! 회원가입 실패 !!!");
		} else {
			System.out.println("회원가입 성공");
			response.sendRedirect(request.getContextPath()+"/");
		}
		
	}

}
