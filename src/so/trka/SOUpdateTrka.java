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
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOUpdateTrka extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trka!");
        }

        Trka t = (Trka) ado;

        if (!t.getDatumVremePocetka().after(new Date())) {
            throw new Exception("Datum i vreme pocetka mora biti posle danasnjeg datuma!");
        }

       

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        // updatujemo trke
        DBBroker.getInstance().update(ado);

        Trka t = (Trka) ado;
        // obrisemo stare prijave
        if(!t.getPrijave().isEmpty()){
            DBBroker.getInstance().delete(t.getPrijave().get(0));
        }

        // dodamo nove
        for (Prijava prijava : t.getPrijave()) {
            DBBroker.getInstance().insert(prijava);
        }

    }

}
