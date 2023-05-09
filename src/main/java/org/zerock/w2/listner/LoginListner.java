package org.zerock.w2.listner;

import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
@Log4j2
public class LoginListner implements HttpSessionAttributeListener {


    @Override
    public void attributeAdded(HttpSessionBindingEvent event){

        String name = event.getName();

        Object obj = event.getValue();

        if(name.equals("loginInfo")){

            log.info("A user logined..........");
            log.info(obj);
        }
    }

}
