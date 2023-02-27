/*
package project.flower;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///C:/Users/ioio6/flower_project/flower/flower/src/main/resources/static/files/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 365);
        */
/* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. *//*

        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 365);
        */
/* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. *//*

        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 365);
        */
/* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. *//*

        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCachePeriod(60 * 60 * 24 * 365);

    }


}
*/
