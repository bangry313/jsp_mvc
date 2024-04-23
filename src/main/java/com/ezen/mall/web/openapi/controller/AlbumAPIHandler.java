package com.ezen.mall.web.openapi.controller;

import com.ezen.mall.web.common.core.handler.APIHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AlbumAPIHandler implements APIHandler {
    @Override
    public void process(Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();

            // 요청 핸들러에서 Open API 사용
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            BufferedReader in = null;
            StringBuilder sb = new StringBuilder();
            int status = connection.getResponseCode();
            String json = null;
            if(status >= 300) {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((json = in.readLine()) != null) {
                    sb.append(json);
                }
            }else {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((json=in.readLine()) != null) {
                    sb.append(json);
                }
            }
            in.close();
            out.print(sb.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
