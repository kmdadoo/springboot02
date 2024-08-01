package com.study.springboot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

//이 클래스를 빈으로 등록하는데 스프링설정으로 사용한다는 의미
@Configuration	
public class WebSecurityConfig 
{  
	// 시큐리티 사용을 위한 빈을 생성한다.
	@Bean  
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{  
		/*
		 CSRF(사이트 간 요청 위조) 공격을 방지하기 위해서 CSRF 토큰을 
		 가진 요청만 처리하도록 방어하고있기 때문이다. 서버에 중요한 
		 영향을 끼칠 수 있는 POST 방식(HTML form, AJAX)은 토큰을 체크
		 한다. GET 방식은 토큰을 체크하지 않는다.
		 
		 CORS란 “Cross-Origin Resource Sharing”의 약자입니다. CORS는 
		 프로토콜인데, 서로 다른 origin일시 리소스와 상호 작용하기 
		 위해 클라이언트인 브라우저에서 실행되는 스크립트입니다. 예를 
		 들어 UI 앱에서 서로 다른 도메인인 API를 호출할 시 CORS로 인해 
		 기본적으로 차단됩니다. 이는 대부분의 브라우저에서 구현되는 
		 W3C의 스펙입니다.
		 따라서 CORS는 보안이나 공격과 같은 문제가 아니라 서로 다른 
		 Orgin 간의 데이터 및 통신을 할 때 브라우저에서 이를 중지하기 
		 위해 제공하는 기본 보호 기능입니다.
		 예를 들어 큰 규모의 IT 기업일 경우 백엔드 서버와 프론트엔드 
		 서버의 IP가 서로 다릅니다. 이때 프론트엔드 서버에서 클라이언
		 트가 로그인을 했을 때, 백엔드의 로그인 API가 호출될 것입니다. 
		 이때 백엔드에서 프론트엔드 도메인을 CORS 허용 설정하지 않으면 
		 접근이 차단되는 것입니다. 반대로 말하자면 JSP, 타임리프와 같은
		 백엔드와 프론트엔드가 같이 구동되는 서버 사이드 렌더링의 경우
		 라면, CORS 설정은 신경쓰지 않아도 무방합니다. 왜냐면 하나의 
		 아이피에 프론트와 백엔드가 구동되기 때문입니다.
		*/
        http.csrf((csrf) -> csrf.disable())
    		.cors((cors) -> cors.disable())
        	.authorizeHttpRequests(request -> request  
        		/*
        			스프링 부트 3.0부터 적용된 스프링 시큐리티 6.0 에서 forward 방식 페이지 
        			이동에도 default로 인증이 걸리도록 변경되어서 JSP나 타임리프 등 컨트롤러에서 
        			화면 파일명을 리턴해 ViewResolver가 작동해 페이지 이동을 하는 경우 이처럼 
        			설정을 해야 한다.
        		*/
        		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        		//  / : 모든 요청명에 대해 권한 없이 접근할 수 있다.
        		.requestMatchers("/").permitAll()
        		// 정적 리소스(static하위)에는 권한없이 접근할 수 있다.
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                // 권한 없이 접근할 수 있다.
                .requestMatchers("/guest/**").permitAll()  // 모두에게 허용.
                // USER, ADMIN 권한 중 하나가 있어야 접근할 수 있다.
                .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN") // 두권한 허용
                // ADMIN 권한만 접근할 수 있다.
                .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 허용
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증 필요
            );
        
        // 로그인 페이지 설정(시큐리티의 디폴트 페이지를 사용한다.)
        http.formLogin((formLogin) ->
        				formLogin.permitAll()); // 기본 로그인 페이지	
        
        http.logout((logout) ->
    				logout.permitAll()); // 로그 아웃 기본 설정 (/logout으로 인증 해제)
		return http.build(); 
    }
 
	@Bean
	// users() 메서드는 빠른 테스트를 위해 등록이 간단한 inMemory 방식의 
	// 인증 사용자를 등록
    protected UserDetailsService users() 
    {
        UserDetails user = User.builder()
        		.username("user")
        		.password(passwordEncoder().encode("1234"))
        		.roles("USER")	// ROLE_USER 에서 ROLE_ 자동으로 붙는다.
        		.build();
        UserDetails admin = User.builder()
        		.username("admin")
        		.password(passwordEncoder().encode("1234"))
        		.roles("USER", "ADMIN")	
        		.build();
        
        // 메모리에 사용자 정보를 담는다.
        return new InMemoryUserDetailsManager(user, admin);
    }
    
	/*
		시큐리티 5 에서는 패스워드를 반드시 암호화해서 저장해야 한다.
		encode() 를 호출할 때마다 패스워드가 변경된다.
	*/
    // passwordEncoder()   
	// 내부적으로 접두어를 붙일 수 있도록 직접 패스워드인코더를 지정하지 않는다.
    public PasswordEncoder passwordEncoder() 
    {
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}