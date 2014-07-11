/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.so.mesto;

import baza.DatabaseBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class VratiMestaSO extends OpstaSO {

    private List<OpstiDomenskiObjekat> lista;

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    public void setLista(List<OpstiDomenskiObjekat> lista) {
        this.lista = lista;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        lista = DatabaseBroker.vratiInstancu().vratiListu(new Mesto());
    }

}
