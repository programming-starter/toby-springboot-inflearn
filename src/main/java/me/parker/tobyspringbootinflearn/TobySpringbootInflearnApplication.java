package me.parker.tobyspringbootinflearn;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class TobySpringbootInflearnApplication {

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // 지우면 안됨, 상위 클래스에서의 onRefresh() 메서드도 어떠한 동작을 하므로...
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                // tomcat, netty, ... 여러가지 웹 서버를 가져오려고 추상화핸놓음.
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start(); // 톰캣 서블릿 컨테이너 동작
            }
        };
        applicationContext.register(TobySpringbootInflearnApplication.class);
        applicationContext.refresh();
    }

}
