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
 * /mvc/board/register-action 요청에 대한 핸들러
 * 게시글 쓰기 처리
 */
public class ArticleRegisterActionHandler implements Handler {
    BoardService boardService = new BoardServiceImpl();
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "redirect:/mvc/board/list";
        String boardId = "10";
        String title = paramMap.get("title");
        String content = paramMap.get("content");
        String writer = paramMap.get("writer");
        String passwd  = "1111";
        Article article = new Article();
        article.setBoardId(Integer.parseInt(boardId));
        article.setTitle(title);
        article.setContent(content);
        article.setWriter(writer);
        article.setPasswd(passwd);
        boardService.writeArticle(article);
        return viewName;
    }
}
