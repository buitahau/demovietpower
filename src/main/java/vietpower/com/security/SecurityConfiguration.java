//package vietpower.com.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationTrustResolver;
//import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * Created by HauKute on 8/7/2018.
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//
//    @Autowired
//    @Qualifier("customUserDetailsService")
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//        auth.authenticationProvider(authenticateProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.sessionManagement().sessionFixation().newSession()
////                .invalidSessionUrl("/login?message=timeout")
////                .maximumSessions(1).expiredUrl("/login?message=max_session").maxSessionsPreventsLogin(true);
//
//        http.authorizeRequests()
////                .antMatchers("/static/**").permitAll()
////                .antMatchers("/", "import/**", "/server/api/**").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .loginProcessingUrl("/login")
//                .usernameParameter("ssoId")
//                .passwordParameter("password")
//                .and()
//                .logout().permitAll()
//                .and()
//                .csrf()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/Access_Denied");
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticateProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationTrustResolver getAuthenticationTrustResolver(){
//        return new AuthenticationTrustResolverImpl();
//    }
//}
