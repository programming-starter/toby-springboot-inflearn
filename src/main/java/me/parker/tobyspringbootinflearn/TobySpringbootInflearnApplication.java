package me.parker.tobyspringbootinflearn;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class TobySpringbootInflearnApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // tomcat, netty, ... 여러가지 웹 서버를 가져오려고 추상화핸놓음.
        WebServer webServer = serverFactory.getWebServer();
        webServer.start(); // 톰캣 서블릿 컨테이너 동작
    }

}
