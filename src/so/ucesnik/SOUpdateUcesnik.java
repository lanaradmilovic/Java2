/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ucesnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Ucesnik;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOUpdateUcesnik extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Ucesnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucesnik!");
        }

        Ucesnik izmeniUcesnik = (Ucesnik) ado;

        ArrayList<Ucesnik> ucesnici = (ArrayList<Ucesnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Ucesnik ucesnik : ucesnici) {
            if (!ucesnik.getUcesnikID().equals(izmeniUcesnik.getUcesnikID())) {
                if (ucesnik.getEmail().equals(izmeniUcesnik.getEmail())) {
                    throw new Exception("Vec postoji ucesnik sa tim emailom!");
                }
                if (ucesnik.getTelefon().equals(izmeniUcesnik.getTelefon())) {
                    throw new Exception("Vec postoji ucesnik sa tim telefonom!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
