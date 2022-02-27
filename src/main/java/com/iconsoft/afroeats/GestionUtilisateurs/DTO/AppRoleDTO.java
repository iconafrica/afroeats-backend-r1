package com.iconsoft.afroeats.GestionUtilisateurs.DTO;

public class AppRoleDTO {
    private Long idrole;
    private String rolename;

    public AppRoleDTO() {
    }

    public AppRoleDTO(String rolename) {
        this.rolename = rolename;
    }

    public Long getIdrole() {
        return idrole;
    }

    public void setIdrole(Long idrole) {
        this.idrole = idrole;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
