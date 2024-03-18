package LoginApplication.CustomApplication.configuration;

import LoginApplication.CustomApplication.service.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsServices customUserDetailsServices;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer

                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/help").permitAll()
                                .requestMatchers("/Contact/").permitAll()
                                .requestMatchers("/task").permitAll()
                                .anyRequest().authenticated()
                )

                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .permitAll()
                                .defaultSuccessUrl("/home", true).permitAll()
                )
                .logout(logout -> logout.permitAll()

                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll()
                )

                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")


                );
        return http.build();
    }
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.authorizeHttpRequests(configurer ->
//						configurer
//								.requestMatchers("/").hasRole("EMPLOYEE")
//								.requestMatchers("/leaders/**").hasRole("MANAGER")
//								.requestMatchers("/systems/**").hasRole("ADMIN")
//								.anyRequest().authenticated()
//				)
//				.formLogin(form ->
//						form
//								.loginPage("/showMyLoginPage")
//								.loginProcessingUrl("/authenticateTheUser")
//								.permitAll()
//				)
//				.logout(logout -> logout.permitAll()
//				)
//				.exceptionHandling(configurer ->
//						configurer.accessDeniedPage("/access-denied")
//				);
//
//		return http.build();
//	}


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServices).passwordEncoder(passwordEncoder());
    }

}

