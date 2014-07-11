/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.so.korisnik;

import baza.DatabaseBroker;
import domen.Korisnik;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class IzmeniKorisnikaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        Korisnik k = (Korisnik) obj;
        DatabaseBroker.vratiInstancu().izmeni(k);
    }

}
