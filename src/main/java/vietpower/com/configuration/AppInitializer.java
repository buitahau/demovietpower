package vietpower.com.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.File;
import java.util.EnumSet;

/**
 * Created by HauKute on 7/18/2018.
 */
public class AppInitializer implements WebApplicationInitializer {
    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        FilterRegistration.Dynamic fr = container.addFilter("encodingFilter", CharacterEncodingFilter.class);
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic siteMesh = container.addFilter("siteMeshFilter", new vietpower.com.filter.SiteMeshFilter());
        EnumSet<DispatcherType> sitemeshDispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        siteMesh.addMappingForUrlPatterns(sitemeshDispatcherTypes, true, "/*");


        FilterRegistration.Dynamic cors = container.addFilter("corsFilter", new vietpower.com.filter.SimpleCORSFilter());
        EnumSet<DispatcherType> corsDispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        cors.addMappingForUrlPatterns(corsDispatcherTypes, true, "/*");
//
        // upload temp file will put here
        File uploadDirectory = new File("X:\\upload");

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        servlet.setMultipartConfig(multipartConfigElement);
    }
}
