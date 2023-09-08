/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sponzor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Sponzor;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetAllSponzor extends AbstractSO {

    private ArrayList<Sponzor> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sponzor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sponzor!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sponzori = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sponzor>) (ArrayList<?>) sponzori;
    }

    public ArrayList<Sponzor> getLista() {
        return lista;
    }

}
