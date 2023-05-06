package org.zerock.w2.controller;


import lombok.extern.java.Log;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
@Log
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException ,IOException {

        log.info("login get........");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{

        log.info("login post.....");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String auto = req.getParameter("auto");

        boolean rememberMe =auto !=null && auto.equals("on");






        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);

            //java.util의 UUID를 사용하면 임의의 번호를 생성 할 수 있다.
            if(rememberMe){
                String uuid = UUID.randomUUID().toString();

                MemberService.INSTANCE.updateUuid(mid,uuid);
                memberDTO.setUuid(uuid);
                // 데이터 베이스 uuid에 난수 추가 확인후
                // remember-me 이름의 쿠키를 생성해서 전송.

                Cookie rememberCookie =
                        new Cookie("remember-me",uuid);

                rememberCookie.setMaxAge(60*60*24*7);
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);
            }


            //정상적으로 로그인 된 경우 HttpSession을 이용해서 'logininfo'라는 이름으로 객체를 저장
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list");

            // 예외 발생시 /login으로 이동하면서 'result'라는 파라미터를 전달
        } catch (Exception e ){
            resp.sendRedirect("/login?result=error");
        }


    }

}
