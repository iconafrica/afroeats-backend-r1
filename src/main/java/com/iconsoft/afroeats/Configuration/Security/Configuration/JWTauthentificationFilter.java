package com.iconsoft.afroeats.Configuration.Security.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTauthentificationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTauthentificationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //ObjectMapper permet de recuperer un    objet json et de le stoker (rendre) dans une class java
        try {
            AppUser appUser=new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            System.out.println("***********************************"+appUser.getEmail());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( appUser.getEmail(),appUser.getPassword()));
        } catch (IOException e) {
            throw new ErrorMessages("L'utilisateur n'a pas été identifié", HttpStatus.CONFLICT);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser= (User) authResult.getPrincipal();//on connait le user(springuser) on va utiliser ses information pour generer le token

        List<String> roles=new ArrayList<>();
        authResult.getAuthorities().forEach(r->{
            roles.add(r.getAuthority());
        });
        String jwt= Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SecurityConstant.SECRET)
                .claim("roles",springUser.getAuthorities())
                .compact();
        System.out.println("le json web token est : "+jwt);
        response.addHeader(SecurityConstant.HEADER_STRING,SecurityConstant.TOKEN_PREFIX+jwt);
        System.out.println("la reponse du json web token est : "+response.getHeaderNames()+"status est "+response.getStatus()+" et le type "+response.getContentType());
        //super.successfulAuthentication(request, response, chain, authResult);
    }
}
