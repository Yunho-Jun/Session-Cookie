package org.zerock.w2.controller;


import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
                                                        ServletException, IOException {

        try{
            Long tno = Long.parseLong(req.getParameter("tno"));

            TodoDTO todoDTO = todoService.get(tno);

            //데이터 담기
            req.setAttribute("dto" , todoDTO);

            //쿠키 찾기
          /*  viewTodos 이름의 쿠키를 찾고 (findCookie()), 쿠키의 내용을 검사한후 조회한적이 없는 번호일경우
                    쿠키의 내용물을 갱신해서 브라우저로 보내줌 쿠키를 변경시에는 경로와 유효시간을 다시 세팅해야함.*/

            Cookie viewTodoCookie = findCookie(req.getCookies(),"viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            if(todoListStr != null && todoListStr.indexOf(tno+"_")>=0){
                exist = true;

            }

            log.info("exist:" +exist);

            if(!exist){
                todoListStr +=tno+"_";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }


            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");

        }

    }
    private Cookie findCookie(Cookie[] cookies, String cookieName){

        Cookie targetCookie = null;

        if(cookies != null && cookies.length>0){
            for(Cookie ck:cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie ==null){
            targetCookie = new Cookie(cookieName,"");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }


}
