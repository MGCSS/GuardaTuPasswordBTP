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
public class GestorClaves {
    private Misclaves miclaves;
    private Session sesion;
    private Transaction tx;    

    public GestorClaves() {
        this.miclaves= null;
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


    public List<Object []> listarClaves() throws HibernateException {
        String hql= "select * from misclaves";
        List <Object []> listaClaves= null;
        
        this.iniciaOperacion();
        
        Query query= sesion.createSQLQuery(hql);
        listaClaves= query.list();
        
//        if (listaClaves.isEmpty() != true) {
//            return listaClaves;
//        }
        
        this.finalizaOperacion();
        
        return listaClaves;
    }
    
    public boolean nuevaClaves(Misclaves misclaves){
        boolean check= false;
        
        try {
            iniciaOperacion();
            sesion.save(misclaves);
            check = true;
            this.miclaves = misclaves;
            System.out.println("Claves insertado correctamente");
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            finalizaOperacion();
        }
        
        return check;
    }
}
