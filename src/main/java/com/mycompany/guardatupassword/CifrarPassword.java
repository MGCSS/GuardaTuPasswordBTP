/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guardatupassword;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Horizon
 */
public class CifrarPassword {

    public CifrarPassword() {
    }
    
    public String CifrarClave (String clave) throws NoSuchAlgorithmException{
        MessageDigest md= MessageDigest.getInstance("MD5");
        md.update(clave.getBytes());
        byte[] b= md.digest();
        StringBuffer sb= new StringBuffer();
        
        for (byte b1 : b){
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        
        return sb.toString();
    }
}
