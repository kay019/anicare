package org.anicare.jsp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anicare.jsp.board.model.service.BoardService;

/**
 * Servlet implementation class DeleteReplyMissingBoard
 */
@WebServlet("/deleteReplyMissingBoard.bo")
public class DeleteReplyMissingBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReplyMissingBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bCode = Integer.parseInt(request.getParameter("bCode"));
		int cc = Integer.parseInt(request.getParameter("cc"));
		int refBcode = Integer.parseInt(request.getParameter("refBcode"));
		
		int result = new BoardService().deleteReplyMissingBoard(bCode, refBcode);
		
		String path = request.getContextPath();
		if (result > 0) {
			path += "/selectOneMissingBoard.bo?bCode=" + refBcode;
			path += "&cc=" + (cc - 1);
			response.sendRedirect(path);
		}
		else {
			request.setAttribute("message", "실종게시판 댓글 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
