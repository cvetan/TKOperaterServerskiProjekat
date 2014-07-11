/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logika.so.korisnik;

import baza.DatabaseBroker;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class SacuvajKorisnikaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
        Korisnik k = (Korisnik) obj;
        List<OpstiDomenskiObjekat> lk = DatabaseBroker.vratiInstancu().vratiListuPoUslovu(k, k.vratiUsloveZaProveru()[0]);
        if(!lk.isEmpty()) {
            throw new RuntimeException("Korisnik sa tim JMBG već postoji!");
        }
        lk = DatabaseBroker.vratiInstancu().vratiListuPoUslovu(k, k.vratiUsloveZaProveru()[1]);
        if(!lk.isEmpty()) {
            throw new RuntimeException("Korisnik sa tim korisničkim imenom već postoji!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        Korisnik k = (Korisnik) obj;
        DatabaseBroker.vratiInstancu().sacuvaj(k);
    }
    
}
