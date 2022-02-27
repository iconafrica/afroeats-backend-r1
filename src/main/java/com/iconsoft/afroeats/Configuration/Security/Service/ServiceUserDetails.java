package com.iconsoft.afroeats.Configuration.Security.Service;

import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import com.iconsoft.afroeats.GestionUtilisateurs.Services.AppUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("serviceuserdetails")
public class ServiceUserDetails implements UserDetailsService {

    final
    AppUserService appUserSercice;

    public ServiceUserDetails(@Lazy AppUserService appUserSercice) {
        this.appUserSercice = appUserSercice;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AppUser user = appUserSercice.getUserByLogin(login);

        if (user==null)throw new ErrorMessages("L'utilisateur n'a pas été identifié", HttpStatus.NOT_FOUND);
        Collection<GrantedAuthority> authorities=new ArrayList<>();//on a une collection de role
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRolename()));
        });
        return new User(user.getEmail(),user.getPassword(),authorities);
    }

}
