package com.ezen.mall.web.home.controller;

import com.ezen.mall.domain.board.dto.Board;
import com.ezen.mall.domain.board.service.BoardService;
import com.ezen.mall.domain.board.service.BoardServiceImpl;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * /mvc/ 요청에 해당하는 요청 핸들러
 */
public class HomeHandler implements Handler {

	BoardService boardService = new BoardServiceImpl();

	@Override
	public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
		String viewName = "/index";
		List<Board> boardList = boardService.boardList();
		model.setAttribute("boardList", boardList);
		return viewName;
	}
}
