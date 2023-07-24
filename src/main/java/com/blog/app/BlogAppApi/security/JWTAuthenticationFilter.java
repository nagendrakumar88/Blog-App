package com.blog.app.BlogAppApi.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get Token
		
		String requestToken=request.getHeader("Authorization");
		
		//Bearur
		
		System.out.println(requestToken);
		
		String userName=null;
		String token=null;
		if(requestToken!=null&&requestToken.startsWith("Bearer")) {
			
		 token = requestToken.substring(7);
		 try {
		userName= this.jwtTokenHelper.getUsernameFromToken(token);
		 }catch (IllegalArgumentException e) {
			System.out.println("Enable to get JWT Token...");
		}catch (ExpiredJwtException e) {
			System.out.println("JWT token is expired....");
		}catch (MalformedJwtException e) {
			System.out.println("Invailid JWT exception...");
		}
		}else {
			System.out.println("JWT Token does not begain with bearer..");
		}
			
		//onece we get the token then now validate
		if(userName!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = this.userDetailService.loadUserByUsername(userName);
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				
				//sab kuchh sahi cal raha he
				
				//Authontication..
				
				UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Invailide JWT Token.....");
			}
		}else {
			
			System.out.println("User name is null or conext is null");
		}
		
		filterChain.doFilter(request, response);
	}

}
