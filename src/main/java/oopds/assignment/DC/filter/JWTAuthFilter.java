// package oopds.assignment.DC.filter;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import java.io.IOException;
// import java.util.ArrayList;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// @Component
// public class JWTAuthFilter extends OncePerRequestFilter {

// 	@Override
// 	protected void doFilterInternal(
// 		HttpServletRequest req,
// 		HttpServletResponse res,
// 		FilterChain chain
// 	)
// 		throws IOException, ServletException {
// 		System.out.println("I'm here");
// 		String header = req.getHeader("Authorization");

// 		if (header == null || !header.startsWith("Bearer")) {
// 			chain.doFilter(req, res);
// 			return;
// 		}

// 		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
// 		SecurityContextHolder.getContext().setAuthentication(authentication);
// 		chain.doFilter(req, res);
// 	}

// 	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
// 		String token = req.getHeader("Authorization");

// 		if (token != null) {
// 			String user = JWT
// 				.require(Algorithm.HMAC256("secret".getBytes()))
// 				.build()
// 				.verify(token.replace("Bearer", ""))
// 				.getSubject();

// 			if (user != null) {
// 				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
// 			}
// 			return null;
// 		}
// 		return null;
// 	}
// }
