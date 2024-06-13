package itacademy.misbackend.security;

import itacademy.misbackend.filter.CustomAuthorizationFilter;
import itacademy.misbackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepo userRepo;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().
                csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().requestMatchers("/api/login/**", "/api/token/refresh/**",
                "/api", "/api/logout/**").permitAll();
        http.authorizeHttpRequests().requestMatchers(
                        "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html",
                        "/swagger-resources/**", "/webjars/**").permitAll();


        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,
                "/api/departments/**", "/api/doctors/**", "api/services/**").permitAll();

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/patients/**",
                        "/api/medCards/**", "/api/appointments/**", "/api/medicalRecords/**").hasRole("DOCTOR")
                .requestMatchers(HttpMethod.POST, "/api/medicalRecords/**").hasRole("DOCTOR")
                .requestMatchers(HttpMethod.PUT, "/api/medicalRecords/**").hasRole("DOCTOR");

        // закомментила, т.к. пока нет юзера с ролью ADMIN
      /*  http.authorizeHttpRequests().requestMatchers("/api/patients/**", "/api/doctors/**", "/api/departments/**",
                        "/api/services/**", "/api/appointments/**", "/api/medicalRecords/**").hasRole("ADMIN");
*/

        http.authorizeHttpRequests().anyRequest().permitAll();//Временно
        http.apply(CustomSecurityDetails.customDsl(userRepo));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.logout().logoutUrl("/api/logout").logoutSuccessUrl("/api/login").invalidateHttpSession(true);
        return http.build();
    } //Нужно будет добавить позже еще ограничения на доступ к ресурсам
}
