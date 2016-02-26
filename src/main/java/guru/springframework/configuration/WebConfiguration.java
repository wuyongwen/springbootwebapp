package guru.springframework.configuration;

import javax.servlet.Filter;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import guru.springframework.wx.handler.WxMpDemoServer;

@Configuration
public class WebConfiguration {

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    
    @Bean
    ServletRegistrationBean weChatMpServletRegistration(){
    	ServletRegistrationBean weChatMpBean = new ServletRegistrationBean(WxMpDemoServer.getJettyServlet());
    	weChatMpBean.addUrlMappings("/wx/*");
    	return weChatMpBean;
    }
    @Bean
    public Filter characterEncodingFilter() {
    	CharacterEncodingFilter characterEncodingFilter =new CharacterEncodingFilter();
    	characterEncodingFilter.setEncoding("UTF-8");
    	characterEncodingFilter.setForceEncoding(true);
    	return characterEncodingFilter;
    }
}
