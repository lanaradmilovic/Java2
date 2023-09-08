/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.prijava;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prijava;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetAllPrijava extends AbstractSO {

    private ArrayList<Prijava> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Prijava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prijava!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> prijave = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Prijava>) (ArrayList<?>) prijave;
    }

    public ArrayList<Prijava> getLista() {
        return lista;
    }

}
