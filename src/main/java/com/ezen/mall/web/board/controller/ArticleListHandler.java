package com.ezen.mall.web.board.controller;

import com.ezen.mall.domain.board.dto.Article;
import com.ezen.mall.domain.board.service.BoardService;
import com.ezen.mall.domain.board.service.BoardServiceImpl;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import com.ezen.mall.web.common.page.PageParams;
import com.ezen.mall.web.common.page.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * /mvc/board/list 요청에 대한 핸들러
 * 게시글 목록 처리
 */
public class ArticleListHandler implements Handler {

    private BoardService boardService = new BoardServiceImpl();

    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "/board/list";

        // 사용자 요청 게시판 번호
        int boardId = 10;
        if(request.getParameter("boardId") != null){
            boardId = Integer.parseInt(request.getParameter("boardId"));
        }
        // 사용자 요청 페이지번호
        int requestPage = 1;
        if(request.getParameter("page") != null){
            requestPage = Integer.parseInt(request.getParameter("page"));
        }
        // 테이블당 보여지는 행의 개수
        int rowCount = 10;
        if(request.getParameter("count") != null){
            rowCount = Integer.parseInt(request.getParameter("count"));
        }

        // 페이지에 보여주는 페이지 번호수
        int pageSize = 10;

        // 사용자 검색 유형
        String searchType = request.getParameter("type"); // null, "", "t", "c",.....
        // 사용자 검색값
        String searchValue = request.getParameter("value");

        List<Article> list = boardService.articleList(rowCount, requestPage, searchType, searchValue);
        model.setAttribute("list", list);

        // 페이지 처리를 위한 테이블 행의 갯수
        int tableRowCount = boardService.getArticleCount(searchType, searchValue);

        PageParams params = new PageParams(rowCount, pageSize, requestPage, tableRowCount);
        Pagination pagination = new Pagination(params);
        model.setAttribute("pagination", pagination);
        return viewName;
    }
}
