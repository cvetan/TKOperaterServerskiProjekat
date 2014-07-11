/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logika.so.racun;

import baza.DatabaseBroker;
import domen.Racun;
import domen.StavkaRacuna;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class SacuvajRacunSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        Racun r = (Racun) obj;
        DatabaseBroker.vratiInstancu().sacuvaj(r);
        for(StavkaRacuna sr: r.getListaStavki()) {
            DatabaseBroker.vratiInstancu().sacuvaj(sr);
            DatabaseBroker.vratiInstancu().izmeni(sr.getZaduzenje());
        }
        DatabaseBroker.vratiInstancu().izmeni(r.getKorisnik());
    }
    
}
