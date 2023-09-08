/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Sponzor;
import domain.Odrediste;
import domain.Prijava;
import domain.Ucesnik;
import domain.Trka;
import java.util.ArrayList;
import so.administrator.SOGetAllAdministrator;
import so.sponzor.SOGetAllSponzor;
import so.odrediste.SOGetAllOdrediste;
import so.prijava.SOGetAllPrijava;
import so.login.SOLogin;
import so.ucesnik.SOAddUcesnik;
import so.ucesnik.SODeleteUcesnik;
import so.ucesnik.SOGetAllUcesnik;
import so.ucesnik.SOUpdateUcesnik;
import so.trka.SOAddTrka;
import so.trka.SODeleteTrka;
import so.trka.SOGetAllTrka;
import so.trka.SOUpdateTrka;

/**
 *
 * @author Korisnik
 */
public class ServerController {

    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addUcesnik(Ucesnik ucesnik) throws Exception {
        (new SOAddUcesnik()).templateExecute(ucesnik);
    }

    public void addTrka(Trka trka) throws Exception {
        (new SOAddTrka()).templateExecute(trka);
    }

    public void deleteUcesnik(Ucesnik ucesnik) throws Exception {
        (new SODeleteUcesnik()).templateExecute(ucesnik);
    }

    public void deleteTrka(Trka trka) throws Exception {
        (new SODeleteTrka()).templateExecute(trka);
    }

    public void updateUcesnik(Ucesnik ucesnik) throws Exception {
        (new SOUpdateUcesnik()).templateExecute(ucesnik);
    }

    public void updateTrka(Trka trka) throws Exception {
        (new SOUpdateTrka()).templateExecute(trka);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Ucesnik> getAllUcesnik() throws Exception {
        SOGetAllUcesnik so = new SOGetAllUcesnik();
        so.templateExecute(new Ucesnik());
        return so.getLista();
    }

    public ArrayList<Trka> getAllTrka() throws Exception {
        SOGetAllTrka so = new SOGetAllTrka();
        so.templateExecute(new Trka());
        return so.getLista();
    }

    public ArrayList<Odrediste> getAllOdrediste() throws Exception {
        SOGetAllOdrediste so = new SOGetAllOdrediste();
        so.templateExecute(new Odrediste());
        return so.getLista();
    }

    public ArrayList<Sponzor> getAllSponzor() throws Exception {
        SOGetAllSponzor so = new SOGetAllSponzor();
        so.templateExecute(new Sponzor());
        return so.getLista();
    }

    public ArrayList<Prijava> getAllPrijava(Trka t) throws Exception {
        SOGetAllPrijava so = new SOGetAllPrijava();
        
        Prijava p = new Prijava();
        p.setTrka(t);
        
        so.templateExecute(p);
        return so.getLista();
    }

    public Object getAllPrijavaVoznje(Trka trka) throws Exception {
           SOGetAllPrijava so = new SOGetAllPrijava();
        
        Prijava p = new Prijava();
        p.setTrka(trka);
        
        so.templateExecute(p);
        return so.getLista();
    }

  

}
