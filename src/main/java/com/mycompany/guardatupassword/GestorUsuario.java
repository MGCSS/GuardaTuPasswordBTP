/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guardatupassword;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Horizon
 */
public class GestorUsuario {
    private Session sesion;
    private Transaction tx;
    private Usuarios usuario;

    public GestorUsuario() {
        this.usuario= null;
    }
    
    public void iniciaOperacion() throws HibernateException {
        System.out.println("Comenzando con Hibernate");
        sesion = HibernateUtil.getSessionFactory().openSession(); //iniciamos una sesion hibernate
        tx = sesion.beginTransaction(); // comienza la transaccion
    }

    public void finalizaOperacion() throws HibernateException {
        System.out.println("Finalizando con Hibernate");
        tx.commit();
        sesion.close();
    }

    public void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        System.out.println("Ocurrió un error en la capa de acceso a datos " + he.getMessage());
        System.exit(0);
    }    
    
    public Usuarios getUsuarios() throws HibernateException {
        String hql= "select * from usuarios";
        List <Object> listaUsuario;
        
        Object temp[]= new Object[2];
        
        this.iniciaOperacion();
        
        Query query= sesion.createSQLQuery(hql);
        listaUsuario= query.list();
        
        if (listaUsuario.isEmpty() != true) {
            this.usuario= new Usuarios();
            
            temp= (Object[]) listaUsuario.get(0);
            
            this.usuario.setNombreUsuario(temp[0].toString());
            this.usuario.setPassword(temp[1].toString());
        }
        
        this.finalizaOperacion();
        
        return this.usuario;        
    }
    
    public boolean comprobarUsuario() {
        boolean check= false;
        
        if (this.usuario != null) {
            check= true;
        }
        return check;
    }
    
    
    public boolean confirmaUsuario(String usuario){
        boolean check= false;
        
        if (this.usuario.getNombreUsuario().compareTo(usuario) == 0){
            check= true;
        }
        
        return check;
    }
    
    
    public boolean confirmaPassword(String password){
        boolean check= false;
        
        if (this.usuario.getPassword().compareTo(password) == 0){
            check= true;
        }
        
        return check;
    }
    
    public boolean guardaUsuario(Usuarios usuario) {
        boolean check= false;
        try {
            iniciaOperacion();
            sesion.save(usuario);
            check= true;
            this.usuario= usuario;
            System.out.println("Usuario insertado correctamente");
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }  
        return check;
    }
    
    
    public boolean modificaPassword(Usuarios usuario) {
        boolean check= false;
        
        try {
            iniciaOperacion();
            sesion.update(usuario);
            this.usuario= usuario;
            check= true;
            System.out.println("Usuario ha sido actualizado correctamente");
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }

        return check;
    }
}
