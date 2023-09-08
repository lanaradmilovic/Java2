/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trka;


import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prijava;
import domain.Trka;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import so.AbstractSO;


/**
 *
 * @author Korisnik
 */
public class SOAddTrka extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Voznja!");
        }

        Trka t = (Trka) ado;

        if (!t.getDatumVremePocetka().after(new Date())) {
            throw new Exception("Datum i vreme pocetka mora biti posle danasnjeg datuma!");
        }

        if (t.getPocetnoOdrediste().getOdredisteID().equals(t.getKrajnjeOdrediste().getOdredisteID())) {
            throw new Exception("Pocetno i kranje odrediste moraju biti razliciti!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long trkaID = tableKeys.getLong(1);

        Trka t = (Trka) ado;
        t.setTrkaID(trkaID);

        for (Prijava prijava : t.getPrijave()) {
            prijava.setTrka(t);
            DBBroker.getInstance().insert(prijava);
            
        }

    }

    
}
