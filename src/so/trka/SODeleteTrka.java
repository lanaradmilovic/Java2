/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trka;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trka;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SODeleteTrka extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trka!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
