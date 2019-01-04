package vietpower.com.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by HauKute on 7/18/2018.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "vietpower.com")
public class AppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieDomain("myAppLocaleCookie");
        resolver.setCookieMaxAge(60*60);//60 mins
        return resolver;
    }

    // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
    // resources/static
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/*");
    }

    // cau hinh welcome file
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/admin/collection/list");
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver getMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}
