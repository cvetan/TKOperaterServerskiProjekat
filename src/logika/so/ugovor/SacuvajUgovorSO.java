/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logika.so.ugovor;

import baza.DatabaseBroker;
import domen.Ugovor;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class SacuvajUgovorSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        Ugovor u = (Ugovor) obj;
        DatabaseBroker.vratiInstancu().sacuvaj(u);
    }
    
}
