package com.mycompany.guardatupassword;
// Generated 20-may-2017 11:16:05 by Hibernate Tools 4.3.1



/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private String nombreUsuario;
     private String password;

    public Usuarios() {
    }

    public Usuarios(String nombreUsuario, String password) {
       this.nombreUsuario = nombreUsuario;
       this.password = password;
    }
   
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


