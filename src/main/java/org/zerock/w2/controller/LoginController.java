package org.zerock.w2.controller;


import lombok.extern.java.Log;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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



        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
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
