package com.ezen.mall.web.board.controller;

import com.ezen.mall.domain.board.dto.Article;
import com.ezen.mall.domain.board.service.BoardService;
import com.ezen.mall.domain.board.service.BoardServiceImpl;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * /mvc/board/read 요청에 대한 핸들러
 * 게시글 상세 화면
 */
public class ArticleReadHandler implements Handler {
    private BoardService boardService = new BoardServiceImpl();
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "/board/read";
        String boardId = paramMap.get("boardId");
        String articleId = paramMap.get("articleId");

        Article article = boardService.getArticle(Integer.parseInt(boardId), Integer.parseInt(articleId));
        model.setAttribute("article", article);
        return viewName;
    }
}
