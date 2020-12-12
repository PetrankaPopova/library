/*
package library.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

public class JwtTokenVerifired extends OncePerRequestFilter{

	private final SecretKey secretKey;

	public JwtTokenVerifired(SecretKey secretKey) {
		this.secretKey = secretKey;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String autherizationHeader = request.getHeader(JwtConstant.AUTHORIZATION_HEADER);
		
		if(Strings.isNullOrEmpty(autherizationHeader) || !autherizationHeader.startsWith(JwtConstant.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = autherizationHeader.replace(JwtConstant.TOKEN_PREFIX, "");
		
		try {

			Jws<Claims> claimsJws = Jwts.parser()
					.setSigningKey(this.secretKey)
					.parseClaimsJws(token);
			
			Claims body = claimsJws.getBody();
			String username = body.getSubject();
			var authorities = (List<Map<String, String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthority = authorities
					.stream().map(sga -> new SimpleGrantedAuthority(sga.get("authority")))
					.collect(Collectors.toSet());
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(
						username,
						null,
						simpleGrantedAuthority
					);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);			
 		}catch (JwtException e){
 			throw new IllegalStateException(
 						String.format("Token %s cannot be trusted", token));
 		}
		
		filterChain.doFilter(request, response);
	}

}
*/
