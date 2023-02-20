package kh.home.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.board.model.service.BoardService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet({"/", "/main", "/index", "/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService service = new BoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aaa = request.getParameter("aaa");
		
		// 2. DB 갔다와서 게시글 불러오기
		// DTO, DAO, Service, Controller, view 
		
		// Paging 처리
		// 1) 총 글 개수 DB에서 알아오기
		int cnt = 0;
		cnt = service.getCountBoard();
		
		// 2) 한페이지에 나타날 글 개수 정하기(보통 = 10개)
		int pageSize = 3; 
		
		// 3) 페이지버튼 개수(보통 5개)
		int pageBlock = 2;
		
		// 4) 현재페이지
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(aaa);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 5) row num 범위 정하기(한페이지에 나타날 글 개수의)
		int startRnum = 0;
		int endRnum = 0;
		startRnum = ((currentPage-1) * pageSize) + 1;
		endRnum = (startRnum + pageSize - 1 > cnt)? cnt : startRnum + pageSize - 1;
		System.out.println(startRnum);
		System.out.println(endRnum);
		
//		request.setAttribute("boardlist", new BoardService().getBoardList());
		request.setAttribute("boardlist", service.getBoardList(startRnum, endRnum));
		
		// 6) 페이지 범위 정하기
		int startPageNum = 0;
		int endPageNum = 0;
		
		startPageNum = (currentPage % pageBlock == 0) ? 
				((currentPage/pageBlock - 1) * pageBlock + 1)
				: ((currentPage/pageBlock) * pageBlock + 1);
		// pageCnt = 총페이지수
		int pageCnt = (cnt/pageSize) + (cnt%pageSize==0 ? 0:1);		
		// ex. cnt-8, pageSize-3 총페이지 수는 3개(3 + 3 + 2)여야 함.
//		int pageCnt = (cnt/pageSize);
//		if(cnt%pageSize!=0) {
//			pageCnt++;
//		}
		
		
		endPageNum = (startPageNum + pageBlock - 1 > pageCnt) ? pageCnt : startPageNum + pageBlock - 1;
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}

	

}
