/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.odrediste;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Odrediste;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetAllOdrediste extends AbstractSO {

    private ArrayList<Odrediste> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Odrediste)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Odrediste!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> odrediste = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Odrediste>) (ArrayList<?>) odrediste;
    }

    public ArrayList<Odrediste> getLista() {
        return lista;
    }

}
