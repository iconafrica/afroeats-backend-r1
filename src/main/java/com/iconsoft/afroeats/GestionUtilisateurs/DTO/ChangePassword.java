package com.iconsoft.afroeats.GestionUtilisateurs.DTO;

public class ChangePassword {
    private String oldpassword;
    private String newpassword;
    private String referenceuser;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getReferenceuser() {
        return referenceuser;
    }

    public void setReferenceuser(String referenceuser) {
        this.referenceuser = referenceuser;
    }
}
