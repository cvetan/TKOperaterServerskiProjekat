/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.so;

import baza.DatabaseBroker;

/**
 *
 * @author cvetan
 */
public abstract class OpstaSO {

    public final synchronized void izvrsiOperaciju(Object obj) throws RuntimeException {
        try {
            DatabaseBroker.vratiInstancu().ucitajDrajver();
            DatabaseBroker.vratiInstancu().otvoriKonekciju();
            proveriPreduslov(obj);
            izvrsiKonkretnuOperaciju(obj);
            DatabaseBroker.vratiInstancu().commitTransakcije();
        } catch (RuntimeException e) {
            DatabaseBroker.vratiInstancu().rollbackTransakcije();
            throw e;
        } finally {
            DatabaseBroker.vratiInstancu().zatvoriKonekciju();
        }
    }

    protected abstract void proveriPreduslov(Object obj) throws RuntimeException;

    protected abstract void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException;
    
}
