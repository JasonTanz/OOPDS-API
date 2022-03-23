package oopds.assignment.DC.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import oopds.assignment.DC.models.Donor;

public class authFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public authFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // setFilterProcessesUrl("/api/auth/**/login");
        // setFilterProcessesUrl("/api/auth/ngo/login");
        // setFilterProcessesUrl("/api/auth/dc/login");
    }
    

    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        // TODO Auto-generated method stub
        // maybe need to change to object mapper
        String email = request.getParameter("email");
        System.out.println("here");
        System.out.println(email);
        String password = request.getParameter("password");
        System.out.println(password);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        System.out.println(authToken);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        System.out.println("here123");

        // Donor donor = (Donor) authentication.getPrincipal();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        System.out.println("here");
        // secret key
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString())
                // .withClaim("permission_level", user.getAuthorities())
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
