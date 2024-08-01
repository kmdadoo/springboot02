package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;

@Configuration	
public class WebSecurityConfig 
{  
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;	// 변수 선언
	
	@Bean  
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{  
        http.csrf((csrf) -> csrf.disable())
    		.cors((cors) -> cors.disable())
        	.authorizeHttpRequests(request -> request  
        		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        		.requestMatchers("/").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/guest/**").permitAll()  // 모두에게 허용.
                .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN") // 두권한 허용
                .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 허용
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증 필요
            );
        
        http.formLogin((formLogin) -> formLogin
    			.loginPage("/loginForm") 			// default : /login
    		    .loginProcessingUrl("/j_spring_security_check")	// 바꾸면 안됨.
//    	        .failureUrl("/loginForm?error") 			// default : /login?error
    		    .failureHandler(authenticationFailureHandler)	// 2 단계. 지정 선언 
    		    .usernameParameter("j_username")	// default : j_username
    	        .passwordParameter("j_password") 	// default : j_password
    	        .permitAll()
    	    );
        
        http.logout((logout) -> logout
    	        .logoutUrl("/logout") // default
    	        .logoutSuccessUrl("/")	// 처음 페이지로
    	        .permitAll()
    	    );
        
        return http.build(); 
    }
 
//	@Bean
//    protected UserDetailsService users() 
//    {
//        UserDetails user = User.builder()
//        		.username("user")
//        		.password(passwordEncoder().encode("1234"))
//        		.roles("USER")	// ROLE_USER 에서 ROLE_ 자동으로 붙는다.
//        		.build();
//        UserDetails admin = User.builder()
//        		.username("admin")
//        		.password(passwordEncoder().encode("1234"))
//        		.roles("USER", "ADMIN")	
//        		.build();
//        
//        // 메모리에 사용자 정보를 담는다.	
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    
//    public PasswordEncoder passwordEncoder() 
//    {
//    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		// 데이터베이스 접속 정보를 먼저 이용
		.dataSource(dataSource)
		// 쿼리로 해당 사용자가 있는지를 먼저 조회한다
		.usersByUsernameQuery("select name as userName, password, enabled"
							+ " from user_list where name = ?")
		// 사용자의 역할을 구해온다
		.authoritiesByUsernameQuery("select name as userName, authority "
								+" from user_list where name = ?")
		// 입력한 비밀번호를 암호화해서 데이터베이스의 암호와 비교를 해서 
		// 올바른 값인지 검증
		.passwordEncoder(new BCryptPasswordEncoder());
		// 쿼리문에 사용되는 userName, password, enabled, authority는 Spring에서 
		// 지정된 컬럼명입니다.
		// 만일 테이블 컬럼과 다를 경우 별칭(Alias)을 줘서 변경하도록 합니다.
		// enabled 의 값이 0이면 비활성, 1이면 활성
	}
}