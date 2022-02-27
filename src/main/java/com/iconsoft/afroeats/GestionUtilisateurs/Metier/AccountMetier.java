package com.iconsoft.afroeats.GestionUtilisateurs.Metier;

import com.iconsoft.afroeats.GestionUtilisateurs.DTO.*;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;

public interface AccountMetier {
    AppUserDTO registerClient(RegisterDTO registerDTO);
    AppUserDTO loginAccount(LoginDTO loginDTO);
    AppUserDTO registerDriver(RegisterDTO registerDTO);
    AppUserDTO confirmeidentity(DocumentCompleter documentCompleter);
    String changePassword(ChangePassword changePassword);
}
