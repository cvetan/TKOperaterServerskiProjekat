/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.so.racun;

import baza.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import domen.Racun;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class VratiPoslednjiRacunSO extends OpstaSO {

    private OpstiDomenskiObjekat racun;

    public OpstiDomenskiObjekat getRacun() {
        return racun;
    }

    public void setRacun(OpstiDomenskiObjekat racun) {
        this.racun = racun;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        Racun r = (Racun) obj;
        racun = DatabaseBroker.vratiInstancu().vratiPoslednjiElement(r);
    }

}
