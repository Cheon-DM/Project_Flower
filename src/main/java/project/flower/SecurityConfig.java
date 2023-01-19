package project.flower;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.flower.service.MemberDetailService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final MemberDetailService memberDetailService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 해당 경로 접근 허용
        http.authorizeHttpRequests().requestMatchers("/", "/signup", "/cart", "/index.html", "/loginProc",
                        "/signupProc", "/signup/**", "/login/**", "/css/**", "/favicon.ico", "/assets/**", "/js/**")
                .permitAll().anyRequest().authenticated();

        // 로그인 페이지 지정
        http.formLogin().loginPage("/login/customer")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/").permitAll();

        // 로그아웃 페이지
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

}
