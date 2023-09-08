/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trka;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trka;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetAllTrka extends AbstractSO {

    private ArrayList<Trka> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trka!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> trke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Trka>) (ArrayList<?>) trke;
    }

    public ArrayList<Trka> getLista() {
        return lista;
    }

}
