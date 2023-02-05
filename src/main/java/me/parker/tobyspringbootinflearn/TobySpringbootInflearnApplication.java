package me.parker.tobyspringbootinflearn;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobySpringbootInflearnApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // tomcat, netty, ... 여러가지 웹 서버를 가져오려고 추상화핸놓음.
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어 등 공통 기능
                    if (req.getRequestURI().equals("/hello")
                            && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        // response
                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().print("Hello " + name);
                    } else if (req.getRequestURI().equals("/user")) {
                        //
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start(); // 톰캣 서블릿 컨테이너 동작
    }

}
