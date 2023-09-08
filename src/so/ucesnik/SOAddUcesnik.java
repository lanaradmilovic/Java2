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
public class SOAddUcesnik extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Ucesnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucesnik!");
        }

        Ucesnik noviUcesnik = (Ucesnik) ado;

        ArrayList<Ucesnik> ucesnici = (ArrayList<Ucesnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik.getEmail().equals(noviUcesnik.getEmail())) {
                throw new Exception("Vec postoji ucesnik sa tim emailom!");
            }
            if (ucesnik.getTelefon().equals(noviUcesnik.getTelefon())) {
                throw new Exception("Vec postoji ucesnik sa tim telefonom!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
